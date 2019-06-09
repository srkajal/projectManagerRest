package org.kajal.mallick.dao;

import org.kajal.mallick.entities.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectDao {
    List<Project> findAllProjects();

    Project findByProjectId(long projectId);

    Project save(Project project);

    int updateProjectDetails(String projectName, LocalDate startDate, LocalDate endDate, int priority, long projectId);

    int updateProjectStatus(String status, long projectId);

    void deleteProject(long projectId);
}
