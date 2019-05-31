package org.kajal.mallick.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.exception.TaskException;
import org.kajal.mallick.facade.TaskManagerFacade;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ExtendedParentTaskListResponse;
import org.kajal.mallick.model.response.ExtendedTaskListResponse;
import org.kajal.mallick.model.response.ExtendedTaskResponse;
import org.kajal.mallick.util.TaskManagerConstant;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TaskManagerServiceImplTest {

    String TASK_NAME = "Task Name 1";
    @InjectMocks
    private TaskManagerServiceImpl taskManagerService;
    @Mock
    private TaskManagerFacade taskManagerFacade;
    private Task task;
    private ParentTask parentTask;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        parentTask = new ParentTask();
        parentTask.setParentTaskName(TASK_NAME);
        parentTask.setParentId(1);

        task = new Task();
        task.setTaskName(TASK_NAME);
        task.setTaskId(1);
        task.setPriority(1);
        task.setStatus(TaskManagerConstant.STATUS_OPEN);
        task.setEndDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setParentTask(parentTask);
    }

    @Test
    public void findAllTasks() {
        when(taskManagerFacade.findAllTasks()).thenReturn(Collections.singletonList(task));
        ExtendedTaskListResponse extendedTaskListResponse = taskManagerService.findAllTasks();

        Assert.assertEquals(1, extendedTaskListResponse.getTasks().size());
        Assert.assertEquals(TASK_NAME, extendedTaskListResponse.getTasks().get(0).getTask());
    }

    @Test
    public void findAllTasksNoTask() {
        when(taskManagerFacade.findAllTasks()).thenReturn(null);
        ExtendedTaskListResponse extendedTaskListResponse = taskManagerService.findAllTasks();

        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), extendedTaskListResponse.getBaseResponse().getStatus());
    }

    @Test
    public void findTaskById() {
        when(taskManagerFacade.findTaskById(anyLong())).thenReturn(task);
        ExtendedTaskResponse extendedTaskResponse = taskManagerService.findTaskById(1l);

        Assert.assertNotNull(task);
        Assert.assertEquals(TASK_NAME, extendedTaskResponse.getTaskDto().getTask());
    }

    @Test
    public void findTaskByIdNoTask() {
        when(taskManagerFacade.findTaskById(anyLong())).thenReturn(null);
        ExtendedTaskResponse extendedTaskResponse = taskManagerService.findTaskById(1l);

        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), extendedTaskResponse.getBaseResponse().getStatus());
    }

    @Test
    public void saveTask() {
        when(taskManagerFacade.saveTask(any(TaskRequest.class))).thenReturn(task);
        BaseResponse baseResponse = taskManagerService.saveTask(new TaskRequest());

        Assert.assertEquals(HttpStatus.CREATED.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void saveTaskUnableToSave() {
        when(taskManagerFacade.saveTask(any(TaskRequest.class))).thenReturn(null);
        BaseResponse baseResponse = taskManagerService.saveTask(new TaskRequest());

        Assert.assertNotNull(task);
        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void update() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskId(1);
        when(taskManagerFacade.update(any(TaskRequest.class))).thenReturn(task);
        BaseResponse baseResponse = taskManagerService.update(taskRequest);

        Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void updateUnableToUpdate() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskId(1);
        when(taskManagerFacade.update(any(TaskRequest.class))).thenReturn(null);
        BaseResponse baseResponse = taskManagerService.update(taskRequest);

        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void closeTaskById() {
        when(taskManagerFacade.closeTaskById(anyLong())).thenReturn(1);
        BaseResponse baseResponse = taskManagerService.closeTaskById(1l);

        Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void closeTaskByIdNoTask() {
        when(taskManagerFacade.closeTaskById(anyLong())).thenReturn(0);
        BaseResponse baseResponse = taskManagerService.closeTaskById(1l);

        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test(expected = TaskException.class)
    public void closeTaskByIdExc() {
        when(taskManagerFacade.closeTaskById(anyLong())).thenReturn(1);
        BaseResponse baseResponse = taskManagerService.closeTaskById(0l);

        Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void findAllParentTasks() {
        when(taskManagerFacade.findAllParentTasks()).thenReturn(Collections.singletonList(parentTask));
        ExtendedParentTaskListResponse extendedParentTaskListResponse = taskManagerService.findAllParentTasks();

        Assert.assertEquals(1, extendedParentTaskListResponse.getParentTasks().size());
        Assert.assertEquals(TASK_NAME, extendedParentTaskListResponse.getParentTasks().get(0).getParentTask());
    }

    @Test
    public void findAllParentTasksNoTask() {
        when(taskManagerFacade.findAllParentTasks()).thenReturn(null);
        ExtendedParentTaskListResponse extendedParentTaskListResponse = taskManagerService.findAllParentTasks();

        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), extendedParentTaskListResponse.getBaseResponse().getStatus());
    }

    @Test
    public void saveParentTask() {
        when(taskManagerFacade.saveParentTask(any(ParentTaskRequest.class))).thenReturn(parentTask);
        BaseResponse baseResponse = taskManagerService.saveParentTask(new ParentTaskRequest());

        Assert.assertEquals(HttpStatus.CREATED.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void saveParentTaskUnableToSave() {
        when(taskManagerFacade.saveParentTask(any(ParentTaskRequest.class))).thenReturn(null);
        BaseResponse baseResponse = taskManagerService.saveParentTask(new ParentTaskRequest());

        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }
}