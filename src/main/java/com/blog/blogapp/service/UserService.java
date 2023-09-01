package com.blog.blogapp.service;

import com.blog.blogapp.dto.RegisterDto;
import com.blog.blogapp.entity.User;

public interface UserService {

    void saveUser(RegisterDto registrationDto);

    User findByEmail(String email);
}
