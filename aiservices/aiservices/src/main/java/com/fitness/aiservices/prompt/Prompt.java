package com.fitness.aiservices.prompt;

import com.fitness.aiservices.model.Activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Prompt {


    public String createPromptForActivity(Activity activity)
    {
        return String.format("""
                Analyze this fitness activity and provide detailed recommendation in the following EXACT JSON format:
                {
                "analysis":{
                    "overall":"Overall analysis here",
                    "pace":"Pace analysis here',
                    "heartRete":"Heart rate analysis here",
                    "caloriesBurned":"calories Burned analysis here"
                    },
                    "improvement":[
                             {
                             "area":"Area name",
                             "recommendation":"Detailed recommendation"
                             }
                    ],
                    "suggestions":[
                               {
                               "workout":"Workout name",
                                "description": "Detailed workout description"
                                 }
                       ],
                    "safety":[
                        "Safety point 1",
                        "Safety point 2"
                        ]
                    }
                    Analyze this activity:
                    Activity Type : %s
                    Duration: %d minutes
                    Calories Burned: %d
                    Additional Metrics: %s
                    Provide detailed analysis focusing on performance,improvement,next workout suggestion and 
                    safety guidelines.
                    Ensure the response following the EXACT JSON format show above.
                    """,
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getAdditionalMetrics()
        );
    }
}

