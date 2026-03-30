package com.fitness.userserviceApplication.controller;

import com.fitness.userserviceApplication.dto.RegisterRequest;
import com.fitness.userserviceApplication.dto.UserResponse;
import com.fitness.userserviceApplication.models.User;
import com.fitness.userserviceApplication.services.userServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class userController {

    private userServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request)
    {
        System.out.println("hi1");
        UserResponse userResponse = userServices.register(request);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find-all-user")
    public ResponseEntity<List<User>> findalluser()
    {
        List<User> userList = userServices.getAllUser();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    @GetMapping("findIdByEmail/{email}")
    public ResponseEntity<Long> findIdByEmail(@PathVariable("email") String email)
    {
        Long id  = userServices.findIdByEmail(email);
        return new ResponseEntity<Long>(id,HttpStatus.OK);
    }

}
