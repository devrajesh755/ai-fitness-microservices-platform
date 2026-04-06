package com.fitness.aiservices.services;

import com.fitness.aiservices.model.Activity;
import com.fitness.aiservices.model.Recommendation;
import com.fitness.aiservices.prompt.Prompt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAiProcessServices {

    @Autowired
    private Prompt promptService;
    @Autowired
    private OpenAiServices openAiServices;

    public Recommendation generateRecommendation(Activity activity)
    {
        //create prompt
        String prompt = promptService.createPromptForActivity(activity);
        //get recommendation form ai model
        String aiResponse = openAiServices.getRecommendations(prompt);
        log.info("RESPONSE FROM AI {} ",aiResponse);
         return processAiResponse(activity,aiResponse);
    }



    private Recommendation processAiResponse(Activity activity, String aiResponse) {

        try
        {
            //clear JSON
            String cleanJson = aiResponse
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();
            log.info("AI RESPONSE {} ",cleanJson);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(cleanJson);

            //analysis recommendation
             JsonNode analysisNode = rootNode.get("analysis");
             StringBuilder fullAnalysis = new StringBuilder();
             addAnalysisSection(fullAnalysis,analysisNode,"overall","Overall");
             addAnalysisSection(fullAnalysis,analysisNode,"pace","Pace");
             addAnalysisSection(fullAnalysis,analysisNode,"heartRete","HeartRete");
             addAnalysisSection(fullAnalysis,analysisNode,"caloriesBurned","CaloriesBurned");

           //improvement
            JsonNode improvementNode = rootNode.get("improvement");
            List<String> improvements = extractImprovement(improvementNode);

           //suggestion
            JsonNode suggestionNode  = rootNode.get("suggestions");
            List<String> suggestions=extractSuggestion(suggestionNode);


            //safety
           JsonNode safetyNode =  rootNode.path("safety");
           List<String> safetys = extractSafety(safetyNode);


            //return Recommendation Object
            return Recommendation.builder()
                    .activityId(activity.getId())
                    .userId(activity.getUserId())
                    .type(activity.getType().toString())
                    .recommendation(fullAnalysis.toString())
                    .improvement(improvements)
                    .suggestion(suggestions)
                    .safety(safetys)
                    .build();
        }
        catch (Exception e)
        {
           e.printStackTrace();
           return createDefaultRecommendation(activity);
        }

    }




    //Json To txt Format analysis Convert
    private void addAnalysisSection(StringBuilder fullAnalysis, JsonNode analysisNode, String key, String prefix)
    {
        if(!analysisNode.path(key).isMissingNode())
        {
            fullAnalysis.append(prefix)
                    .append(analysisNode.path(key).asString())
                    .append("\n\n");
        }
    }


    //improvement Convert : Json To List<String> Format
    private List<String> extractImprovement(JsonNode improvementNode) {

        List<String> improvements = new ArrayList<>();
        if(improvementNode.isArray())
        {
            improvementNode.forEach(improvement->{
                String area=improvement.path("area").asString();
                String detail = improvement.path("recommendation").asString();
                improvements.add(String.format("%s : %s",area,detail));
            });
        }
        return improvements.isEmpty() ?
                Collections.singletonList("No specific improvement provided")
                :improvements;
    }

    //Suggestions Convert : Json To List<String> Format
    public List<String> extractSuggestion(JsonNode suggestionNode)
    {
        List<String> Suggestions = new ArrayList<>();
        if(suggestionNode.isArray())
        {
            suggestionNode.forEach(suggestion->{
                String workout = suggestionNode.path("workout").asString();
                String description = suggestionNode.path("description").asString();
                Suggestions.add(String.format("%s : %s",workout,description));
            });
        }
        return Suggestions.isEmpty() ?
                Collections.singletonList("No specific suggestion provided")
                :Suggestions;
    }

    //safety Convert : Json To List<String> Format
    public List<String> extractSafety(JsonNode safetyNode)
    {
        List<String> safetys = new ArrayList<>();
        if(safetyNode.isArray())
        {
            safetyNode.forEach(item->safetys.add(item.asString()));
        }
        return safetys.isEmpty()?
                Collections.singletonList("")
                :safetys;
    }

    private Recommendation createDefaultRecommendation(Activity activity)
    {
        return Recommendation.builder()
                .activityId(activity.getId())
                .userId(activity.getUserId())
                .type(activity.getType().toString())
                .recommendation("Unable To Generate Detailed Analysis")
                .improvement(Collections.singletonList("Continue With Your Current Routine"))
                .suggestion(Collections.singletonList("Consider Consulting A Fitness Consultant"))
                .safety(Arrays.asList(
                        "Stay hydrated before, during, and after exercise",
                        "Warm up properly and cool down after workouts",
                        "Listen to your body and stop if you feel pain or discomfort",
                        "Avoid overexertion and increase intensity gradually",
                        "Use proper form and technique to prevent injuries",
                        "Wear appropriate footwear and comfortable clothing",
                        "Consult a doctor before starting any new fitness program if you have health conditions"
                ))
                .build();
    }

}
