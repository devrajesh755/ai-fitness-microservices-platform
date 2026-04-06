package com.fitness.aiservices.services;




import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.openai.OpenAiChatModel;

import org.springframework.stereotype.Service;

@Service
@Slf4j

public class OpenAiServices {

    private  ChatClient openAiChatModel;

    public OpenAiServices(OpenAiChatModel openAiChatModel)
    {
        this.openAiChatModel= ChatClient.builder(openAiChatModel).build();
    }


    public String getRecommendations(String details)
    {
        String response = openAiChatModel.prompt(details).call().content();
        return response;
    }

}
