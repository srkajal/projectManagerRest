package org.kajal.mallick.facade;

import org.kajal.mallick.entities.Project;
import org.kajal.mallick.model.request.ProjectRequest;

import java.util.List;

public interface ProjectFacade {
    List<Project> findAllProjects();

    List<Project> findAllActiveProjects();

    Project findByProjectId(long projectId);

    Project saveProject(ProjectRequest projectRequest);

    int updateProject(ProjectRequest projectRequest);

    int suspendProject(long projectId);

    int activateProject(long projectId);

    void deleteProject(long projectId);
}
