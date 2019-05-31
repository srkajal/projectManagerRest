package org.kajal.mallick.service;

import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ExtendedParentTaskListResponse;
import org.kajal.mallick.model.response.ExtendedTaskListResponse;
import org.kajal.mallick.model.response.ExtendedTaskResponse;

public interface TaskManagerService {

    ExtendedTaskListResponse findAllTasks();

    ExtendedTaskResponse findTaskById(long taskId);

    BaseResponse saveTask(TaskRequest taskRequest);

    BaseResponse update(TaskRequest taskRequest);

    BaseResponse closeTaskById(long taskId);

    ExtendedParentTaskListResponse findAllParentTasks();

    BaseResponse saveParentTask(ParentTaskRequest parentTaskRequest);
}
