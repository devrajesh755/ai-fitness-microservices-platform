package com.fitness.userserviceApplication.services;

import com.fitness.userserviceApplication.Repository.UserRepository;
import com.fitness.userserviceApplication.dto.RegisterRequest;
import com.fitness.userserviceApplication.dto.UserResponse;
import com.fitness.userserviceApplication.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@AllArgsConstructor
public class userServices {

    @Autowired
   private UserRepository repo;


    public UserResponse register(RegisterRequest request)
    {
       if(repo.exitsEmailId(request.getEmail())==1)  //if exit return true
       {
              throw new RuntimeException("Email Already Exist ! Please Enter a new Email");
       }

       User user = new User();
       user.setEmail(request.getEmail());
       user.setPassword(request.getPassword());
       user.setFirstname(request.getFirstname());
       user.setLastname(request.getLastname());

       //Save Use In Db
       User savedUser = repo.save(user);

       UserResponse userResponse = new UserResponse();
       //set userResponse
       userResponse.setId(savedUser.getId());
       userResponse.setEmail(savedUser.getEmail());
       userResponse.setPassword(savedUser.getPassword());
       userResponse.setFirstname(savedUser.getFirstname());
       userResponse.setLastname(savedUser.getLastname());
       userResponse.setCreateAt(savedUser.getCreateAt());
       userResponse.setUpdatedAt(savedUser.getUpdatedAt());
       return userResponse;
    }

    public List<User> getAllUser()
    {
        return repo.findAll();
    }

    public User getUserProfileEmail(String email)
    {
        if(repo.exitsEmailId(email)==0)
        {
            throw new RuntimeException("Email Id Dose Not Exit");
        }
        return repo.getUserByEmail(email);
    }

    public String deleteUserByEmail(String email)
    {
        if(repo.exitsEmailId(email)==0)
        {
            return  "Email Dose Not Exit";
        }
        repo.deleteUserByEmail(email);
        return "DELETE SUCCESSFULLY";
    }

    public Boolean exitUserid(String id)
    {
        return repo.existsById(id);
    }
}
