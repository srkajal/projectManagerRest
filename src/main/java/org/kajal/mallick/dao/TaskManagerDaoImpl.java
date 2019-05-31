package org.kajal.mallick.dao;


import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.repositories.ParentTaskManagerRepository;
import org.kajal.mallick.repositories.TaskManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TaskManagerDaoImpl implements TaskManagerDao {

    private TaskManagerRepository taskManagerRepository;

    private ParentTaskManagerRepository parentTaskManagerRepository;

    @Autowired
    public TaskManagerDaoImpl(TaskManagerRepository taskManagerRepository, ParentTaskManagerRepository parentTaskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
        this.parentTaskManagerRepository = parentTaskManagerRepository;
    }

    public List<Task> findAllTasks() {
        return taskManagerRepository.findAll();
    }

    @Override
    public Task findTaskById(long taskId) {
        return taskManagerRepository.findById(taskId).orElse(null);
    }

    @Override
    public Task saveTask(Task task) {
        return taskManagerRepository.save(task);
    }

    @Override
    public int updateTaskStatus(String status, long taskId) {
        return taskManagerRepository.updateTaskStatus(status, taskId);
    }

    @Override
    public List<ParentTask> findAllParentTasks() {
        return parentTaskManagerRepository.findAll();
    }

    @Override
    public ParentTask saveParentTask(ParentTask parentTask) {
        return parentTaskManagerRepository.save(parentTask);
    }
}
