package com.fitness.activityservices.service;

import com.fitness.activityservices.config.userClient;
import com.fitness.activityservices.dto.ActivityRequest;
import com.fitness.activityservices.dto.ActivityResponse;
import com.fitness.activityservices.model.Activity;
import com.fitness.activityservices.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class ActivityService {

    @Autowired
    private  ActivityRepository repo;

    @Autowired
    private userClient userClient;

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

        return mapToResponse(savedActivity);
    }

    public ActivityResponse mapToResponse(Activity request)
    {
        ActivityResponse activityResponse = new ActivityResponse();
        //set value
        activityResponse.setId(request.getId());
        activityResponse.setUserId(request.getUserId());
        activityResponse.setType(request.getType());
        activityResponse.setDuration(request.getDuration());
        activityResponse.setCaloriesBurned(request.getCaloriesBurned());
        activityResponse.setStartTime(request.getStartTime());
        activityResponse.setAdditionalMetrics(request.getAdditionalMetrics());
        activityResponse.setCreateAt(request.getCreateAt());
        activityResponse.setUpdateAt(request.getUpdateAt());
        return activityResponse;
    }


    //Chack User Id Exit Or Not Using Fegin Client
    public Boolean ifExistUser(@PathVariable("id") String id)
    {
        return  userClient.validUserId(id);
    }

    //Chack Activity Id Exit Or Not Using Repository
    public boolean ifExistActivity(String activityId)
    {
        return repo.existsById(activityId);
    }
}