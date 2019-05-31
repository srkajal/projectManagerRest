package org.kajal.mallick.dao;

import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;

import java.util.List;

public interface TaskManagerDao {

    List<Task> findAllTasks();

    Task findTaskById(long taskId);

    Task saveTask(Task task);

    int updateTaskStatus(String status, long taskId);

    List<ParentTask> findAllParentTasks();

    ParentTask saveParentTask(ParentTask parentTask);
}
