package com.example.projectmanagement.services;

import com.example.projectmanagement.dao.UserAccountDao;
import com.example.projectmanagement.ds.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    public UserAccount save(UserAccount userAccount) {
        return userAccountDao.save(userAccount);
    }
}
