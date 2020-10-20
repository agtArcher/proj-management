package com.example.projectmanagement.controllers;

import com.example.projectmanagement.ds.RegisterData;
import com.example.projectmanagement.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {



    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/register")
    public String register(@ModelAttribute("registerData") RegisterData registerData) {

        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid RegisterData registerData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "security/register";
        }
        //encoder take user's raw password, encode it and replace raw password to encoded password
        userAccountService.register(registerData);

        return "redirect:/login";
    }
}
