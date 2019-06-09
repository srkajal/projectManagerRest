package org.kajal.mallick.controller;

import org.kajal.mallick.model.request.ProjectRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.ProjectListResponse;
import org.kajal.mallick.model.response.ProjectResponse;
import org.kajal.mallick.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/findProjectById/{projectId}")
    public ProjectResponse findProjectById(@PathVariable("projectId") long projectId) {
        return projectService.findByProjectId(projectId);
    }

    @PostMapping("/createProject")
    public @ResponseBody
    BaseResponse createProject(@RequestBody @Valid ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest);
    }

    @PostMapping("/updateProject")
    public @ResponseBody
    BaseResponse updateProject(@RequestBody @Valid ProjectRequest projectRequest) {
        return projectService.updateProject(projectRequest);
    }

    @GetMapping("/suspendProject/{projectId}")
    public @ResponseBody
    BaseResponse suspendProject(@PathVariable("projectId") long projectId) {
        return projectService.suspendProject(projectId);
    }

    @GetMapping("/activateProject/{projectId}")
    public @ResponseBody
    BaseResponse activateProject(@PathVariable("projectId") long projectId) {
        return projectService.activateProject(projectId);
    }

    @GetMapping("/delete/{projectId}")
    public BaseResponse deleteById(@PathVariable("projectId") long projectId) {
        return projectService.deleteProject(projectId);
    }

}
