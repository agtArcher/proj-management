package com.example.projectmanagement.services;

import com.example.projectmanagement.dao.UserAccountDao;
import com.example.projectmanagement.ds.RegisterData;
import com.example.projectmanagement.ds.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    public UserAccount save(UserAccount userAccount) {
        return userAccountDao.save(userAccount);
    }

    public void register(RegisterData registerData) {
        UserAccount user = new UserAccount(registerData.getUsername(), registerData.getEmail(), bCryptEncoder.encode(registerData.getPassword()));
        save(user);
    }
}
