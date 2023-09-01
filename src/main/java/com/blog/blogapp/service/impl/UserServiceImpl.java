package com.blog.blogapp.service.impl;

import com.blog.blogapp.dto.RegisterDto;
import com.blog.blogapp.entity.Role;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.repo.RoleRepo;
import com.blog.blogapp.repo.UserRepo;
import com.blog.blogapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepo userRepo,RoleRepo roleRepo,PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegisterDto registrationDto) {
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setName(registrationDto.getFirstName()+" "+registrationDto.getLastName());
        //use spring security to encrypt password
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepo.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
