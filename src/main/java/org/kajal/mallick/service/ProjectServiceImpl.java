package org.kajal.mallick.service;

import org.kajal.mallick.entities.Project;
import org.kajal.mallick.facade.ProjectFacade;
import org.kajal.mallick.model.ProjectDto;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ProjectListResponse;
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
}
