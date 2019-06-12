package org.kajal.mallick.service;

import org.kajal.mallick.model.request.CreateTaskRequest;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.UpdateTaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ParentTaskListResponse;
import org.kajal.mallick.model.response.TaskListResponse;
import org.kajal.mallick.model.response.TaskResponse;

public interface TaskService {

    TaskListResponse findAllTasks();

    TaskListResponse findAllByProjectId(long projectId);

    TaskResponse findTaskById(long taskId);

    BaseResponse saveTask(CreateTaskRequest createTaskRequest);

    BaseResponse update(UpdateTaskRequest updateTaskRequest);

    BaseResponse closeTaskById(long taskId);

    ParentTaskListResponse findAllParentTasks();

    BaseResponse saveParentTask(ParentTaskRequest parentTaskRequest);
}
