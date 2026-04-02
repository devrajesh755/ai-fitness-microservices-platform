package com.fitness.activityservices.service;

import com.fitness.activityservices.config.FeignClient;
import com.fitness.activityservices.dto.ActivityRequest;
import com.fitness.activityservices.dto.ActivityResponse;
import com.fitness.activityservices.model.Activity;
import com.fitness.activityservices.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class ActivityService {

    @Autowired
    private  ActivityRepository repo;

    @Autowired
    private FeignClient feignClient;

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


    public Boolean ifExistUser(@PathVariable("id") String id)
    {
        return  feignClient.validUserId(id);
    }
}
