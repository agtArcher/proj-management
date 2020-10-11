package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectDao extends CrudRepository<Project, Long> {
}
