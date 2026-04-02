package com.fitness.activityservices.config;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient("userserviceApplication")
public interface FeignClient {

    @GetMapping("/api/users/existsById/{userid}")
    boolean validUserId(@PathVariable("userid") String id);
}
