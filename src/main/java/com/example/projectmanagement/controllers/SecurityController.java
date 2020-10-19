package com.example.projectmanagement.controllers;

import com.example.projectmanagement.ds.UserAccount;
import com.example.projectmanagement.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/register")
    public String register(@ModelAttribute("userAccount") UserAccount userAccount) {

        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid UserAccount user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "security/register";
        }
        //encoder take user's raw password, encode it and replace raw password to encoded password
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        userAccountService.save(user);

        return "redirect:/login";
    }
}
