package org.kajal.mallick.facade;

import org.kajal.mallick.dao.TaskDao;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.util.TaskManagerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskFacadeImpl implements TaskFacade {
    private TaskDao taskDao;

    @Autowired
    public TaskFacadeImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskDao.findAllTasks();
    }

    @Override
    public Task findTaskById(long taskId) {
        return taskDao.findTaskById(taskId);
    }

    @Override
    public Task saveTask(TaskRequest taskRequest) {
        ParentTask parentTask = null;
        if (taskRequest.getParentId() > 0) {
            parentTask = new ParentTask(taskRequest.getParentId());
        }

        Task task = new Task(parentTask, taskRequest.getTaskName(), taskRequest.getStartDate(), taskRequest.getEndDate(), taskRequest.getPriority(), TaskManagerConstant.STATUS_OPEN);
        return taskDao.saveTask(task);
    }

    @Override
    public Task update(TaskRequest taskRequest) {
        ParentTask parentTask = null;
        if (taskRequest.getParentId() > 0) {
            parentTask = new ParentTask(taskRequest.getParentId());
        }

        Task task = new Task(taskRequest.getTaskId(), parentTask, taskRequest.getTaskName(), taskRequest.getStartDate(), taskRequest.getEndDate(), taskRequest.getPriority(), TaskManagerConstant.STATUS_OPEN);
        return taskDao.saveTask(task);
    }

    @Override
    public int closeTaskById(long taskId) {
        return taskDao.updateTaskStatus(TaskManagerConstant.STATUS_CLOSED, taskId);
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
