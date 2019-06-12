package org.kajal.mallick.facade;

import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.model.request.CreateTaskRequest;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.UpdateTaskRequest;

import java.util.List;

public interface TaskFacade {
    List<Task> findAllTasks();

    List<Task> findAllByProjectId(long projectId);

    Task findTaskById(long taskId);

    Task saveTask(CreateTaskRequest createTaskRequest);

    int update(UpdateTaskRequest updateTaskRequest);

    int closeTaskById(long taskId);

    List<ParentTask> findAllParentTasks();

    ParentTask saveParentTask(ParentTaskRequest parentTaskRequest);
}
