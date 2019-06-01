package org.kajal.mallick.facade;

import org.kajal.mallick.dao.ProjectDao;
import org.kajal.mallick.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectFacadeImpl implements ProjectFacade {
    private ProjectDao projectDao;

    @Autowired
    public ProjectFacadeImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public List<Project> findAllProjects() {
        return projectDao.findAllProjects();
    }
}
