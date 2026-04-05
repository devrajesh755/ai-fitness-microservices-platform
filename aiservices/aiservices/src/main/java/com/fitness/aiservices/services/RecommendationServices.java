package com.fitness.aiservices.services;

import com.fitness.aiservices.Repository.AiRepository;
import com.fitness.aiservices.model.Recommendation;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RecommendationServices {

    private ChatClient openAiChatModel;

    private RecommendationServices(OpenAiChatModel openAiChatModel)
    {
       this.openAiChatModel= ChatClient.builder(openAiChatModel).build();
    }
    @Autowired
    private  AiRepository repo;

    public List<Recommendation> getUserRecommendation(String userId)
    {
            return repo.findByUserId(userId);
    }
    public Recommendation getActivityRecommendation(String activityId)
    {
        return repo.findByActivityId(activityId)
                .orElseThrow(()->new RuntimeException("No recommendation found by activity"));
    }

    public String  test_chat_services(String  prompt)
    {
        return openAiChatModel.prompt(prompt).call().content();
    }
}
