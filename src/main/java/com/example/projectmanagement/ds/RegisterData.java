package com.example.projectmanagement.ds;

import com.example.projectmanagement.validators.annotations.PasswordMatches;
import com.example.projectmanagement.validators.annotations.UniqueUserEmail;
import com.example.projectmanagement.validators.annotations.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordMatches
public class RegisterData {

    @NotBlank(message = "*Username cannot be empty")
    @Size(min = 4, max = 50, message = "*Size must be between 4 and 50 symbols")
    @UniqueUsername(message = "*Username must be unique")
    private String username;

    @NotBlank(message = "*Email cannot be empty")
    @Email(message = "*Email must be valid")
    @UniqueUserEmail(message = "*Email must be unique")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be more than 5 symbols")
    private String password;

    private String passConfirm;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassConfirm() {
        return passConfirm;
    }

    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
    }

}
