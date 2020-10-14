package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountDao extends CrudRepository<UserAccount, Long> {
}
