package org.kajal.mallick.dao;

import org.kajal.mallick.entities.Project;
import org.kajal.mallick.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Override
    public Project findByProjectId(long projectId) {
        return projectRepository.findProjectByIdWithTasks(projectId).orElse(null);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public int updateProjectDetails(String projectName, LocalDate startDate, LocalDate endDate, int priority, long projectId) {
        return projectRepository.updateProjectDetails(projectName, startDate, endDate, priority, projectId);
    }

    @Override
    @Transactional
    public int updateProjectStatus(String status, long projectId) {
        return projectRepository.updateProjectStatus(status, projectId);
    }

    @Override
    public void deleteProject(long projectId) {
        projectRepository.deleteById(projectId);
    }
}
