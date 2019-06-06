package org.kajal.mallick.dao;

import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskDao {

    List<Task> findAllTasks();

    List<Task> findAllByProjectId(long projectId);

    Task findTaskById(long taskId);

    Task saveTask(Task task);

    int updateTaskStatus(String status, long taskId);

    List<ParentTask> findAllParentTasks();

    ParentTask saveParentTask(ParentTask parentTask);

    int updateTaskDetails(String taskName, LocalDate startDate, LocalDate endDate, int priority, long parentId, long projectId, long taskId);

    void deleteTasksByIds(List<Long> taskIds);
}
