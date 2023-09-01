package com.blog.blogapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private Long id;

    @NotEmpty(message = "FirstName should not be empty or null")
    private String firstName;

    @NotEmpty(message = "LastName should not be empty or null")
    private String lastName;

    @NotEmpty(message = "Email should not be empty or null")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

}
