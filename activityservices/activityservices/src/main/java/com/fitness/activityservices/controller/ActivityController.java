package com.fitness.activityservices.controller;

import com.fitness.activityservices.dto.ActivityRequest;
import com.fitness.activityservices.dto.ActivityResponse;
import com.fitness.activityservices.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/activity")
@AllArgsConstructor
public class ActivityController {

    @Autowired
    private ActivityService service;

    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request)
    {
        service.trackActivity(request);
        return new ResponseEntity<>()
    }

}
