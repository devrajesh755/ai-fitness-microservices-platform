package com.fitness.aiservices.services;

import com.fitness.aiservices.Repository.AiRepository;
import com.fitness.aiservices.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServices {

    @Autowired
    private  AiRepository repo;

    public List<Recommendation> getUserRecommendation(String userId)
    {

    }
    public Recommendation getActivityRecommendation(String activityId)
    {

    }
}
