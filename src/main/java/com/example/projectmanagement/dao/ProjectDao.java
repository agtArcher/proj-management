package com.example.projectmanagement.dao;

import com.example.projectmanagement.ds.Project;
import com.example.projectmanagement.dto.ChartData;
import com.example.projectmanagement.dto.TimeChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apiprojects", path = "apiprojects")
public interface ProjectDao extends PagingAndSortingRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value " +
            "FROM project " +
            "GROUP BY stage")
    Iterable<ChartData> getProjectStatus();

    @Query(nativeQuery = true, value = "SELECT name as projectName, start_date as startDate, end_date as endDate FROM project")
    Iterable<TimeChartData> getTimeData();
}
