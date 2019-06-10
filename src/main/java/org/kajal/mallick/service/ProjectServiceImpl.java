package org.kajal.mallick.service;

import org.kajal.mallick.entities.Project;
import org.kajal.mallick.exception.BaseException;
import org.kajal.mallick.facade.ProjectFacade;
import org.kajal.mallick.model.ProjectDto;
import org.kajal.mallick.model.request.ProjectRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ProjectListResponse;
import org.kajal.mallick.model.response.ProjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private ProjectFacade projectFacade;

    @Autowired
    public ProjectServiceImpl(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @Override
    public ProjectListResponse findAllProjects() {
        ProjectListResponse projectListResponse = new ProjectListResponse();
        BaseResponse baseResponse;

        List<Project> projectList = projectFacade.findAllProjects();

        if (!CollectionUtils.isEmpty(projectList)) {
            projectList
                    .forEach(project -> projectListResponse
                            .getProjects()
                            .add(new ProjectDto(project)));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Number of projects found " + projectList.size());

            logger.info("Find number of project:{}", projectList.size());
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No Project found");
            logger.info("No Project found");
        }

        projectListResponse.setBaseResponse(baseResponse);

        return projectListResponse;
    }

    @Override
    public ProjectListResponse findAllActiveProjects() {
        ProjectListResponse projectListResponse = new ProjectListResponse();
        BaseResponse baseResponse;

        List<Project> projectList = projectFacade.findAllActiveProjects();

        if (!CollectionUtils.isEmpty(projectList)) {
            projectList
                    .forEach(project -> projectListResponse
                            .getProjects()
                            .add(new ProjectDto(project)));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Number of projects found " + projectList.size());

            logger.info("Find number of project:{}", projectList.size());
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No Project found");
            logger.info("No Project found");
        }

        projectListResponse.setBaseResponse(baseResponse);

        return projectListResponse;
    }

    @Override
    public ProjectResponse findByProjectId(long projectId) {
        ProjectResponse projectResponse = new ProjectResponse();
        BaseResponse baseResponse;

        if (projectId <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "ProjectId should not be less than 1");
        }

        Project project = projectFacade.findByProjectId(projectId);

        if (project != null) {
            projectResponse.setProjectDto(new ProjectDto(project));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Project found for Id:" + projectId);
            logger.info("Find project by id:{}", projectId);
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No Project found for Id:" + projectId);
            logger.info("Task not found for id:{}", projectId);
        }

        projectResponse.setBaseResponse(baseResponse);

        return projectResponse;
    }

    @Override
    public BaseResponse createProject(ProjectRequest projectRequest) {
        Project savedProject = projectFacade.saveProject(projectRequest);

        if (savedProject != null) {
            logger.info("Project saved successfully :{}", projectRequest.getProjectName());
            return new BaseResponse(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), "Project saved successfully");
        } else {
            logger.info("Unable to save the project :{}", projectRequest.getProjectName());
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to save task");
        }
    }

    @Override
    public BaseResponse updateProject(ProjectRequest projectRequest) {
        if (projectRequest.getProjectId() <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "ProjectId should not be less than 1");
        }

        int rowUpdated = projectFacade.updateProject(projectRequest);

        if (rowUpdated > 0) {
            logger.info("Project updated successfully :{}", projectRequest.getProjectName());
            return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Project updated successfully");
        } else {
            logger.info("Unable to updated the project :{}", projectRequest.getProjectName());
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to updated project");
        }
    }

    @Override
    public BaseResponse suspendProject(long projectId) {
        if (projectId <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "ProjectId should not be less than 1");
        }

        int rowUpdated = projectFacade.suspendProject(projectId);

        if (rowUpdated > 0) {
            logger.info("Project suspended successfully :{}", projectId);
            return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Project suspended successfully");
        } else {
            logger.info("Unable to suspend the project :{}", projectId);
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to suspended project");
        }
    }

    @Override
    public BaseResponse activateProject(long projectId) {
        if (projectId <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "ProjectId should not be less than 1");
        }

        int rowUpdated = projectFacade.activateProject(projectId);

        if (rowUpdated > 0) {
            logger.info("Project activated successfully :{}", projectId);
            return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Project activated successfully");
        } else {
            logger.info("Unable to activated the project :{}", projectId);
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to activated project");
        }
    }

    @Override
    public BaseResponse deleteProject(long projectId) {

        projectFacade.deleteProject(projectId);
        logger.info("Project deleted successfully :{}", projectId);
        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Project deleted successfully");

    }
}
