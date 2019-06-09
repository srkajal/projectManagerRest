package org.kajal.mallick.service;

import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.exception.BaseException;
import org.kajal.mallick.facade.TaskFacade;
import org.kajal.mallick.model.ParentTaskDto;
import org.kajal.mallick.model.TaskDto;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ParentTaskListResponse;
import org.kajal.mallick.model.response.TaskListResponse;
import org.kajal.mallick.model.response.TaskResponse;
import org.kajal.mallick.util.ProjectManagerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private TaskFacade taskFacade;

    @Autowired
    public TaskServiceImpl(TaskFacade taskFacade) {
        this.taskFacade = taskFacade;
    }

    @Override
    public TaskListResponse findAllTasks() {
        List<Task> taskList = taskFacade.findAllTasks();
        return generateTasksResponseByList(taskList, ProjectManagerConstant.TASKS_SUCCESS_MESSAGE, ProjectManagerConstant.TASKS_FAILURE_MESSAGE);
    }

    @Override
    public TaskListResponse findAllByProjectId(long projectId) {
        List<Task> taskList = taskFacade.findAllByProjectId(projectId);
        return generateTasksResponseByList(taskList, ProjectManagerConstant.TASKS_SUCCESS_MESSAGE, ProjectManagerConstant.TASKS_FAILURE_MESSAGE);
    }

    @Override
    public TaskResponse findTaskById(long taskId) {
        TaskResponse taskResponse = new TaskResponse();
        BaseResponse baseResponse;

        if (taskId <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "TaskId should not be less than 1");
        }

        Task task = taskFacade.findTaskById(taskId);

        if (task != null) {
            taskResponse.setTaskDto(new TaskDto(task));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Task found for Id:" + taskId);
            logger.info("Find task by id:{}", taskId);
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No Task found for Id:" + taskId);
            logger.info("Task not found for id:{}", taskId);
        }

        taskResponse.setBaseResponse(baseResponse);

        return taskResponse;
    }

    @Override
    public BaseResponse saveTask(TaskRequest taskRequest) {

        Task savedTask = taskFacade.saveTask(taskRequest);

        if (savedTask != null) {
            logger.info("Task saved successfully :{}", taskRequest.getTaskName());
            return new BaseResponse(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), "Task saved successfully");
        } else {
            logger.info("Unable to save the task :{}", taskRequest.getTaskName());
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to save task");
        }
    }

    @Override
    public BaseResponse update(TaskRequest taskRequest) {

        if (taskRequest.getTaskId() <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "TaskId should not be less than 1");
        }

        int rowUpdated = taskFacade.update(taskRequest);

        if (rowUpdated > 0) {
            logger.info("Task updated successfully :{}", taskRequest.getTaskName());
            return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Task updated successfully");
        } else {
            logger.info("Unable to update the task :{}", taskRequest.getTaskName());
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to update task");
        }
    }

    @Override
    public BaseResponse closeTaskById(long taskId) {
        int rowUpdated;
        if (taskId <= 0) {
            throw new BaseException(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE.value(), "TaskId should not be less than 1");
        }

        try {
            rowUpdated = taskFacade.closeTaskById(taskId);
        } catch (Exception ex) {
            rowUpdated = 0;
            logger.error("Unable to closed the Task", ex);
        }
        if (rowUpdated <= 0) {

            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Unable to close the task by taskId:" + taskId);
        }

        logger.info("Task closed successfully taskId:{}", taskId);

        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "Task closed successfully for taskId: " + taskId);
    }

    @Override
    public ParentTaskListResponse findAllParentTasks() {
        ParentTaskListResponse parentTaskListResponse = new ParentTaskListResponse();
        BaseResponse baseResponse;

        List<ParentTask> parentTaskList = taskFacade.findAllParentTasks();

        if (!CollectionUtils.isEmpty(parentTaskList)) {
            parentTaskList
                    .forEach(task -> parentTaskListResponse.getParentTasks()
                            .add(new ParentTaskDto(task)));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Number of Parent Tasks found " + parentTaskList.size());

            logger.info("Find number of parent task:{}", parentTaskList.size());
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No Task found");
            logger.info("No Parent Task found");
        }

        parentTaskListResponse.setBaseResponse(baseResponse);

        return parentTaskListResponse;
    }

    @Override
    public BaseResponse saveParentTask(ParentTaskRequest parentTaskRequest) {
        ParentTask savedParentTask = taskFacade.saveParentTask(parentTaskRequest);

        if (savedParentTask != null) {
            logger.info("Parent Task saved successfully :{}", parentTaskRequest.getParentTaskName());
            return new BaseResponse(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), "Parent Task saved successfully");
        } else {
            logger.info("Unable to save the parent task :{}", parentTaskRequest.getParentTaskName());
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to save parent task");
        }
    }

    private TaskListResponse generateTasksResponseByList(List<Task> taskList, String successMessage, String errorMessage) {
        TaskListResponse taskListResponse = new TaskListResponse();
        BaseResponse baseResponse;

        if (!CollectionUtils.isEmpty(taskList)) {
            taskList
                    .forEach(task -> taskListResponse.getTasks()
                            .add(new TaskDto(task)));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), String.format(successMessage, taskList.size()));

            logger.info(String.format(successMessage, taskList.size()));
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), errorMessage);
            logger.info(errorMessage);
        }

        taskListResponse.setBaseResponse(baseResponse);

        return taskListResponse;
    }
}
