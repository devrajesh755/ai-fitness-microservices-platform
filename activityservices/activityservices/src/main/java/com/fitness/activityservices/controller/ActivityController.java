package com.fitness.activityservices.controller;

import com.fitness.activityservices.dto.ActivityRequest;
import com.fitness.activityservices.dto.ActivityResponse;
import com.fitness.activityservices.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity")
@AllArgsConstructor
public class ActivityController {

    @Autowired
    private ActivityService service;



    @PostMapping("/trackActivity")
    public ResponseEntity<?> trackActivity(@RequestBody ActivityRequest request)
    {

        if(service.ifExistUser(request.getUserId()))
        {
            ActivityResponse response  = service.trackActivity(request);
            return new ResponseEntity<ActivityResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<String>("invalid user id ! please enter a valid user is",HttpStatus.BAD_REQUEST);

    }



}
