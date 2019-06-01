package org.kajal.mallick.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.entities.ParentTask;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.exception.TaskException;
import org.kajal.mallick.facade.TaskFacade;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ParentTaskListResponse;
import org.kajal.mallick.model.response.TaskListResponse;
import org.kajal.mallick.model.response.TaskResponse;
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

public class TaskServiceImplTest {

    String TASK_NAME = "Task Name 1";
    @InjectMocks
    private TaskServiceImpl taskManagerService;
    @Mock
    private TaskFacade taskFacade;
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
        when(taskFacade.findAllTasks()).thenReturn(Collections.singletonList(task));
        TaskListResponse taskListResponse = taskManagerService.findAllTasks();

        Assert.assertEquals(1, taskListResponse.getTasks().size());
        Assert.assertEquals(TASK_NAME, taskListResponse.getTasks().get(0).getTask());
    }

    @Test
    public void findAllTasksNoTask() {
        when(taskFacade.findAllTasks()).thenReturn(null);
        TaskListResponse taskListResponse = taskManagerService.findAllTasks();

        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), taskListResponse.getBaseResponse().getStatus());
    }

    @Test
    public void findTaskById() {
        when(taskFacade.findTaskById(anyLong())).thenReturn(task);
        TaskResponse taskResponse = taskManagerService.findTaskById(1l);

        Assert.assertNotNull(task);
        Assert.assertEquals(TASK_NAME, taskResponse.getTaskDto().getTask());
    }

    @Test
    public void findTaskByIdNoTask() {
        when(taskFacade.findTaskById(anyLong())).thenReturn(null);
        TaskResponse taskResponse = taskManagerService.findTaskById(1l);

        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), taskResponse.getBaseResponse().getStatus());
    }

    @Test
    public void saveTask() {
        when(taskFacade.saveTask(any(TaskRequest.class))).thenReturn(task);
        BaseResponse baseResponse = taskManagerService.saveTask(new TaskRequest());

        Assert.assertEquals(HttpStatus.CREATED.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void saveTaskUnableToSave() {
        when(taskFacade.saveTask(any(TaskRequest.class))).thenReturn(null);
        BaseResponse baseResponse = taskManagerService.saveTask(new TaskRequest());

        Assert.assertNotNull(task);
        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void update() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskId(1);
        when(taskFacade.update(any(TaskRequest.class))).thenReturn(task);
        BaseResponse baseResponse = taskManagerService.update(taskRequest);

        Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void updateUnableToUpdate() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTaskId(1);
        when(taskFacade.update(any(TaskRequest.class))).thenReturn(null);
        BaseResponse baseResponse = taskManagerService.update(taskRequest);

        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void closeTaskById() {
        when(taskFacade.closeTaskById(anyLong())).thenReturn(1);
        BaseResponse baseResponse = taskManagerService.closeTaskById(1l);

        Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void closeTaskByIdNoTask() {
        when(taskFacade.closeTaskById(anyLong())).thenReturn(0);
        BaseResponse baseResponse = taskManagerService.closeTaskById(1l);

        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test(expected = TaskException.class)
    public void closeTaskByIdExc() {
        when(taskFacade.closeTaskById(anyLong())).thenReturn(1);
        BaseResponse baseResponse = taskManagerService.closeTaskById(0l);

        Assert.assertEquals(HttpStatus.OK.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void findAllParentTasks() {
        when(taskFacade.findAllParentTasks()).thenReturn(Collections.singletonList(parentTask));
        ParentTaskListResponse parentTaskListResponse = taskManagerService.findAllParentTasks();

        Assert.assertEquals(1, parentTaskListResponse.getParentTasks().size());
        Assert.assertEquals(TASK_NAME, parentTaskListResponse.getParentTasks().get(0).getParentTask());
    }

    @Test
    public void findAllParentTasksNoTask() {
        when(taskFacade.findAllParentTasks()).thenReturn(null);
        ParentTaskListResponse parentTaskListResponse = taskManagerService.findAllParentTasks();

        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), parentTaskListResponse.getBaseResponse().getStatus());
    }

    @Test
    public void saveParentTask() {
        when(taskFacade.saveParentTask(any(ParentTaskRequest.class))).thenReturn(parentTask);
        BaseResponse baseResponse = taskManagerService.saveParentTask(new ParentTaskRequest());

        Assert.assertEquals(HttpStatus.CREATED.getReasonPhrase(), baseResponse.getStatus());
    }

    @Test
    public void saveParentTaskUnableToSave() {
        when(taskFacade.saveParentTask(any(ParentTaskRequest.class))).thenReturn(null);
        BaseResponse baseResponse = taskManagerService.saveParentTask(new ParentTaskRequest());

        Assert.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), baseResponse.getStatus());
    }
}