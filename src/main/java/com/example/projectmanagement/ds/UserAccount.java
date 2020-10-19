package com.example.projectmanagement.ds;

import com.example.projectmanagement.validators.annotations.UniqueUserEmail;
import com.example.projectmanagement.validators.annotations.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_generator")
    @SequenceGenerator(name = "user_accounts_generator", sequenceName = "user_accounts_seq", allocationSize = 1)
    private long userId;

    @Column(name = "username")
    @NotBlank(message = "*Username cannot be empty")
    @Size(min = 4, max = 50, message = "*Size must be between 4 and 50 symbols")
    @UniqueUsername(message = "*Username must be unique")
    private String userName;

    @NotBlank(message = "*Email cannot be empty")
    @Email(message = "*Email must be valid")
    @UniqueUserEmail(message = "*Email must be unique")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be more than 5 symbols")
    private String password;
    private String role;

    private boolean enabled = true;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getUserId() {
        return userId;
    }
}
