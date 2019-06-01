package org.kajal.mallick.dao;

import org.kajal.mallick.entities.Project;
import org.kajal.mallick.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectDaoImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
