package com.fitness.userserviceApplication.controller;

import com.fitness.userserviceApplication.dto.RegisterRequest;
import com.fitness.userserviceApplication.dto.UserResponse;
import com.fitness.userserviceApplication.models.User;
import com.fitness.userserviceApplication.services.userServices;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request)
    {
        UserResponse userResponse = userServices.register(request);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find-all-user")
    public ResponseEntity<List<User>> findalluser()
    {
        List<User> userList = userServices.getAllUser();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    @GetMapping("getUserProfileEmail/{email}")
    public ResponseEntity<User> getUserProfileByEmail(@PathVariable("email") String email)
    {
        User user = userServices.getUserProfileEmail(email);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/deleteUserByEmail/{email}")
    public ResponseEntity<String> deleteUserById(@PathVariable("email") String email)
    {
        String info = userServices.deleteUserByEmail(email);
        return new ResponseEntity<String>(info,HttpStatus.OK);
    }

    @GetMapping("/existsById/{userid}")
    public ResponseEntity<Boolean> existsUserId(@PathVariable("userid") String id)
    {
        Boolean exitedUserid=  userServices.exitUserid(id);
        return new ResponseEntity<Boolean>(exitedUserid,HttpStatus.OK);
    }

}
