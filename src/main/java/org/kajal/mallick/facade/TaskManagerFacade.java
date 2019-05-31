package org.kajal.mallick.facade;

import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;

import java.util.List;

public interface TaskManagerFacade {
    List<Task> findAllTasks();

    Task findTaskById(long taskId);

    Task saveTask(TaskRequest taskRequest);

    Task update(TaskRequest taskRequest);

    int closeTaskById(long taskId);

    List<ParentTask> findAllParentTasks();

    ParentTask saveParentTask(ParentTaskRequest parentTaskRequest);
}
