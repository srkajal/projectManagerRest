package org.kajal.mallick.controller;

import org.kajal.mallick.model.request.CreateTaskRequest;
import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.UpdateTaskRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ParentTaskListResponse;
import org.kajal.mallick.model.response.TaskListResponse;
import org.kajal.mallick.model.response.TaskResponse;
import org.kajal.mallick.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/task")
class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/findAllTasks")
    TaskListResponse findAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping(value = "/findAllTasksByProjectId/{projectId}")
    TaskListResponse findAllTasksByProjectId(@PathVariable("projectId") long projectId) {
        return taskService.findAllByProjectId(projectId);
    }

    @GetMapping("/findAllParentTasks")
    ParentTaskListResponse findAllParentTasks() {
        return taskService.findAllParentTasks();
    }

    @GetMapping("/findTaskById/{taskId}")
    public TaskResponse findTaskById(@PathVariable("taskId") long taskId) {
        return taskService.findTaskById(taskId);
    }

    @GetMapping("/closeTaskById/{taskId}")
    public BaseResponse closeTaskById(@PathVariable("taskId") long taskId) {
        return taskService.closeTaskById(taskId);
    }

    @PostMapping("/createTask")
    public @ResponseBody
    BaseResponse createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        return taskService.saveTask(createTaskRequest);
    }

    @PostMapping("/createParentTask")
    public @ResponseBody
    BaseResponse createParentTask(@RequestBody @Valid ParentTaskRequest parentTaskRequest) {
        return taskService.saveParentTask(parentTaskRequest);
    }

    @PostMapping("/updateTask")
    public @ResponseBody
    BaseResponse updateTask(@RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        return taskService.update(updateTaskRequest);
    }
}
