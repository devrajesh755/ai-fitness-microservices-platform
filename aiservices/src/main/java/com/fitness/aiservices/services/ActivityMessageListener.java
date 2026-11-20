package com.fitness.aiservices.services;

import com.fitness.aiservices.RecommendationRepository.AiRepository;
import com.fitness.aiservices.model.Activity;
import com.fitness.aiservices.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    @Autowired
    private ActivityAiProcessServices activityAiProcessServices;

    @Autowired
    private AiRepository repo;


     @Value("${app.kafka.topic.name}")
    private String topicName;


    @KafkaListener(topics = "${app.kafka.topic.name}",groupId = "activity-proceser-group")
    public void processingActivity(Activity activity)
    {
        log.info("Received Activity For Processing : {}",activity.getUserId());
        Recommendation recommendation = activityAiProcessServices.generateRecommendation(activity);
        repo.save(recommendation);

    }
}
