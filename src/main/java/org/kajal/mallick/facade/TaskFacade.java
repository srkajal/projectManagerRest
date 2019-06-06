package org.kajal.mallick.facade;

import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;

import java.util.List;

public interface TaskFacade {
    List<Task> findAllTasks();

    List<Task> findAllByProjectId(long projectId);

    Task findTaskById(long taskId);

    Task saveTask(TaskRequest taskRequest);

    int update(TaskRequest taskRequest);

    int closeTaskById(long taskId);

    List<ParentTask> findAllParentTasks();

    ParentTask saveParentTask(ParentTaskRequest parentTaskRequest);
}
