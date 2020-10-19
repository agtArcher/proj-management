package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserAccountDao extends PagingAndSortingRepository<UserAccount, Long> {
    Optional<UserAccount> findFirstByEmail(String email);
    Optional<UserAccount> findFirstByUserName(String userName);
}
