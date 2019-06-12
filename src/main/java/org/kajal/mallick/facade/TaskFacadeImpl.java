package org.kajal.mallick.facade;

import org.kajal.mallick.dao.TaskDao;
import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.exception.BaseException;
import org.kajal.mallick.model.request.CreateTaskRequest;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.UpdateTaskRequest;
import org.kajal.mallick.util.ProjectManagerConstant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TaskFacadeImpl implements TaskFacade {
    private TaskDao taskDao;
    private UserDao userDao;

    public TaskFacadeImpl(TaskDao taskDao, UserDao userDao) {
        this.taskDao = taskDao;
        this.userDao = userDao;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskDao.findAllTasks();
    }

    @Override
    public List<Task> findAllByProjectId(long projectId) {
        return taskDao.findAllByProjectId(projectId);
    }

    @Override
    public Task findTaskById(long taskId) {
        return taskDao.findTaskById(taskId);
    }

    @Override
    @Transactional
    public Task saveTask(CreateTaskRequest createTaskRequest) {
        ParentTask parentTask = null;
        if (createTaskRequest.getParentId() > 0) {
            parentTask = new ParentTask(createTaskRequest.getParentId());
        }

        User user = userDao.findByUserId(createTaskRequest.getUserId());

        if (user == null) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "User does not exist");
        }

        Project project = new Project(createTaskRequest.getProjectId());

        if (user.getTask() == null) {
            Task task = new Task(parentTask, project, createTaskRequest.getTaskName(), createTaskRequest.getStartDate(), createTaskRequest.getEndDate(), createTaskRequest.getPriority(), ProjectManagerConstant.STATUS_OPEN);

            Task savedTask = taskDao.saveTask(task);

            userDao.updateTask(savedTask.getTaskId(), user.getUserId());

            return savedTask;
        } else {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "User already have a task");
        }
    }

    @Override
    public int update(UpdateTaskRequest updateTaskRequest) {
        Long parentId = null;

        if (updateTaskRequest.getParentId() != 0) {
            parentId = updateTaskRequest.getParentId();
        }
        return taskDao.updateTaskDetails(updateTaskRequest.getTaskName(), updateTaskRequest.getStartDate(), updateTaskRequest.getEndDate(), updateTaskRequest.getPriority(), parentId, updateTaskRequest.getTaskId());
    }

    @Override
    public int closeTaskById(long taskId) {
        return taskDao.updateTaskStatus(ProjectManagerConstant.STATUS_CLOSED, taskId);
    }

    @Override
    public List<ParentTask> findAllParentTasks() {
        return taskDao.findAllParentTasks();
    }

    @Override
    public ParentTask saveParentTask(ParentTaskRequest parentTaskRequest) {
        return taskDao.saveParentTask(new ParentTask(parentTaskRequest.getParentTaskName()));
    }
}
