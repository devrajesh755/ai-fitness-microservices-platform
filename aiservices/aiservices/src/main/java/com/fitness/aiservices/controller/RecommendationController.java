package com.fitness.aiservices.controller;

import com.fitness.aiservices.model.Recommendation;
import com.fitness.aiservices.services.RecommendationServices;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private final RecommendationServices services;


    @GetMapping("/getAllRecommendation/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable("userId") String userId)
    {
        List<Recommendation> recommendationList = services.getUserRecommendation(userId);
        return new ResponseEntity<List<Recommendation>>(recommendationList, HttpStatus.OK);
    }

    @GetMapping("/getParticulerRecommendation/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable("activityId") String activityId)
    {
        Recommendation recommendation=services.getActivityRecommendation(activityId);
        return new ResponseEntity<Recommendation>(recommendation, HttpStatus.OK);
    }
}
