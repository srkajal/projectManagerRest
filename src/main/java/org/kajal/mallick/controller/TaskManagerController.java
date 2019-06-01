package org.kajal.mallick.controller;

import org.kajal.mallick.model.request.ParentTaskRequest;
import org.kajal.mallick.model.request.TaskRequest;
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
class TaskManagerController {

    private final TaskService taskService;

    @Autowired
    public TaskManagerController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/findAllTasks")
    TaskListResponse findAllTasks() {
        return taskService.findAllTasks();
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
    BaseResponse createTask(@RequestBody @Valid TaskRequest taskRequest) {
        return taskService.saveTask(taskRequest);
    }

    @PostMapping("/createParentTask")
    public @ResponseBody
    BaseResponse createParentTask(@RequestBody @Valid ParentTaskRequest parentTaskRequest) {
        return taskService.saveParentTask(parentTaskRequest);
    }

    @PostMapping("/updateTask")
    public @ResponseBody
    BaseResponse updateTask(@RequestBody @Valid TaskRequest taskRequest) {
        return taskService.update(taskRequest);
    }
}
