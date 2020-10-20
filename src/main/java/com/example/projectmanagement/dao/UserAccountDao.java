package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "api-useraccounts", path = "api-useraccounts")
public interface UserAccountDao extends PagingAndSortingRepository<UserAccount, Long> {
    Optional<UserAccount> findFirstByEmail(String email);
    Optional<UserAccount> findFirstByUserName(String userName);
}
