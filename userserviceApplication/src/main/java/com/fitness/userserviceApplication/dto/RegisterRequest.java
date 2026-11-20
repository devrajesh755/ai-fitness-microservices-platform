package com.fitness.userserviceApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {



    @NotBlank(message="Email Is Required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password Is Required")
    @Size(min=6,max = 15,message = "Password Must Have AtLest 6 nad AtMost 15 Character")
    private String password;

    private String firstname;
    private String lastname;
}
