package org.kajal.mallick.dao;


import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.repositories.ParentTaskRepository;
import org.kajal.mallick.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class TaskDaoImpl implements TaskDao {

    private TaskRepository taskRepository;

    private ParentTaskRepository parentTaskRepository;

    @Autowired
    public TaskDaoImpl(TaskRepository taskRepository, ParentTaskRepository parentTaskRepository) {
        this.taskRepository = taskRepository;
        this.parentTaskRepository = parentTaskRepository;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAllTasks();
    }

    @Override
    public List<Task> findAllByProjectId(long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }

    @Override
    public Task findTaskById(long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public int updateTaskStatus(String status, long taskId) {
        return taskRepository.updateTaskStatus(status, taskId);
    }

    @Override
    public List<ParentTask> findAllParentTasks() {
        return parentTaskRepository.findAll();
    }

    @Override
    public ParentTask saveParentTask(ParentTask parentTask) {
        return parentTaskRepository.save(parentTask);
    }

    @Override
    public int updateTaskDetails(String taskName, LocalDate startDate, LocalDate endDate, int priority, long parentId, long projectId, long taskId) {
        return taskRepository.updateTaskDetails(taskName, startDate, endDate, priority, parentId, projectId, taskId);
    }

    @Override
    public void deleteTasksByIds(List<Long> taskIds) {
        taskRepository.deleteTaksByIds(taskIds);
    }
}
