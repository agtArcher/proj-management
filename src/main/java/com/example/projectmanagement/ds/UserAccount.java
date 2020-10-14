package com.example.projectmanagement.ds;

import javax.persistence.*;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_generator")
    @SequenceGenerator(name = "user_accounts_generator", sequenceName = "user_accounts_seq", allocationSize = 1)
    private long userId;

    @Column(name = "username")
    private String userName;

    private String email;
    private String password;
    private String role;

    private boolean enabled;

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
