package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.Project;
import com.example.projectmanagement.dto.ChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProjectDao extends PagingAndSortingRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value " +
            "FROM project " +
            "GROUP BY stage")
    Iterable<ChartData> getProjectStatus();
}
