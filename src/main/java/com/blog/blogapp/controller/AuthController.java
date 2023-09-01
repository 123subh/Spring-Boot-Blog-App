package com.blog.blogapp.controller;

import com.blog.blogapp.dto.RegisterDto;
import com.blog.blogapp.entity.User;
import com.blog.blogapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegisterDto user = new RegisterDto();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegisterDto user,
                           BindingResult result,
                           Model model
    ){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null &&existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already a user with same email id");
        }

        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }


}
