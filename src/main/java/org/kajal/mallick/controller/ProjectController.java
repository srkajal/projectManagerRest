package org.kajal.mallick.controller;

import org.kajal.mallick.model.response.ProjectListResponse;
import org.kajal.mallick.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/findAllProjects")
    ProjectListResponse findAllProjects() {
        return projectService.findAllProjects();
    }

    /*@GetMapping("/findAllParentTasks")
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
    }*/
}
