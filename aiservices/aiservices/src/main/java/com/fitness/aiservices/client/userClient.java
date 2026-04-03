package com.fitness.aiservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userserviceApplication")
public interface userClient {

    @GetMapping("/api/users/existsById/{userid}")
    boolean validUserId(@PathVariable("userId") String userid);
}
