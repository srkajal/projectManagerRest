package org.kajal.mallick.service;

import org.kajal.mallick.model.request.ProjectRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ProjectListResponse;
import org.kajal.mallick.model.response.ProjectResponse;

public interface ProjectService {
    ProjectListResponse findAllProjects(boolean detailsRequired);

    ProjectListResponse findAllActiveProjects();

    ProjectResponse findByProjectId(long projectId);

    BaseResponse createProject(ProjectRequest projectRequest);

    BaseResponse updateProject(ProjectRequest projectRequest);

    BaseResponse suspendProject(long projectId);

    BaseResponse activateProject(long projectId);

    BaseResponse deleteProject(long projectId);
}
