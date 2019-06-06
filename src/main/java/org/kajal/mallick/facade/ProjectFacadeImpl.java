package org.kajal.mallick.facade;

import org.kajal.mallick.dao.ProjectDao;
import org.kajal.mallick.dao.TaskDao;
import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.exception.ProjectException;
import org.kajal.mallick.model.request.ProjectRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectFacadeImpl implements ProjectFacade {
    private ProjectDao projectDao;
    private UserDao userDao;
    private TaskDao taskDao;

    public ProjectFacadeImpl(ProjectDao projectDao, UserDao userDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.taskDao = taskDao;
    }

    @Override
    public List<Project> findAllProjects() {
        return projectDao.findAllProjects();
    }

    @Override
    public Project findByProjectId(long projectId) {
        return projectDao.findByProjectId(projectId);
    }

    @Override
    @Transactional
    public Project saveProject(ProjectRequest projectRequest) {

        User user = userDao.findByUserId(projectRequest.getUserId());

        if (user == null) {
            throw new ProjectException("User does not exist");
        }

        if (user.getProject() == null) {
            Project project = projectDao.save(new Project(projectRequest.getProjectName(), projectRequest.getStartDate(), projectRequest.getEndDate(), projectRequest.getPriority()));

            userDao.updateProject(project.getProjectId(), projectRequest.getUserId());

            return project;
        } else {
            throw new ProjectException("User already have a project");
        }
    }

    @Override
    public int updateProject(ProjectRequest projectRequest) {
        return projectDao.updateProjectDetails(projectRequest.getProjectName(), projectRequest.getStartDate(), projectRequest.getEndDate(), projectRequest.getPriority(), projectRequest.getProjectId());
    }

    @Override
    @Transactional
    public void deleteProject(long projectId) {
        List<Task> tasks = taskDao.findAllByProjectId(projectId);

        if (!CollectionUtils.isEmpty(tasks)) {
            List<Long> taskIds = tasks.stream().map(t -> t.getTaskId()).collect(Collectors.toList());
            int rowUpdatedTasks = userDao.removeTasksFromUser(taskIds);

            if (rowUpdatedTasks <= 0) {
                throw new ProjectException("Unable to remove tasks from user");
            }

            taskDao.deleteTasksByIds(taskIds);
        }

        userDao.removeProjectFromUser(projectId);

        projectDao.deleteProject(projectId);
    }
}
