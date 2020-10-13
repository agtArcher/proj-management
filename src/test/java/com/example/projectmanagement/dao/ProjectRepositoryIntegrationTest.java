package com.example.projectmanagement.dao;

import com.example.projectmanagement.ProjectManagementApplication;
import com.example.projectmanagement.ds.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:drop.sql"})
})
public class ProjectRepositoryIntegrationTest {

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void ifNewProjectSaved_thenSuccess() {
        Project project = new Project("New test project", "COMPLETED", "Test Description");
        projectDao.save(project);

        Assert.assertEquals(5, projectDao.findAll().size());
    }
}
