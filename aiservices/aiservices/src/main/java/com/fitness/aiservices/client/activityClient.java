package com.fitness.aiservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("activityservices")
public  interface  activityClient {

    @GetMapping("/api/activity/existsById/{activityId}")
    Boolean validActivityId(@PathVariable("activityId") String activityId);

}
