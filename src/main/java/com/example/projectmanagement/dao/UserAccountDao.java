package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAccountDao extends PagingAndSortingRepository<UserAccount, Long> {
}
