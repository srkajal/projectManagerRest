package org.kajal.mallick.service;

import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ParentTaskListResponse;
import org.kajal.mallick.model.response.TaskListResponse;
import org.kajal.mallick.model.response.TaskResponse;

public interface TaskService {

    TaskListResponse findAllTasks();

    TaskResponse findTaskById(long taskId);

    BaseResponse saveTask(TaskRequest taskRequest);

    BaseResponse update(TaskRequest taskRequest);

    BaseResponse closeTaskById(long taskId);

    ParentTaskListResponse findAllParentTasks();

    BaseResponse saveParentTask(ParentTaskRequest parentTaskRequest);
}
