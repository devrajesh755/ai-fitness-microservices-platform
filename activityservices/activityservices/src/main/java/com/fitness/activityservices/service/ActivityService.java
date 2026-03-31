package com.fitness.activityservices.service;

import com.fitness.activityservices.dto.ActivityRequest;
import com.fitness.activityservices.dto.ActivityResponse;
import com.fitness.activityservices.model.Activity;
import com.fitness.activityservices.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class ActivityService {

    @Autowired
    private  ActivityRepository repo;

    public ActivityResponse trackActivity(ActivityRequest request)
    {
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = repo.save(activity);
    }

}
