package org.kajal.mallick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.util.ProjectManagerConstant;

import java.io.Serializable;
import java.util.List;

public class ProjectDto implements Serializable {
    @JsonProperty("project_id")
    private long projectId;
    @JsonProperty("project_name")
    private String projectName;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    private int priority;
    @JsonProperty("no_of_tasks")
    private long noOfTasks;
    @JsonProperty("no_of_tasks_completed")
    private long noOfTasksCompleted;
    @JsonProperty("user_id")
    private long userId;
    private String status;

    public ProjectDto() {
    }

    public ProjectDto(long projectId, String projectName, String startDate, String endDate, int priority, long noOfTasks) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.noOfTasks = noOfTasks;
    }

    public ProjectDto(Project project) {
        this(project.getProjectId(), project.getProjectName(), String.valueOf(project.getStartDate()), String.valueOf(project.getEndDate()), project.getPriority(), project.getTasks().size());
        this.noOfTasksCompleted = getCountOfCompletedTasks(project.getTasks());
        this.status = project.getStatus();
        if (project.getUser() != null) {
            this.userId = project.getUser().getUserId();
        }

    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getNoOfTasks() {
        return noOfTasks;
    }

    public void setNoOfTasks(long noOfTasks) {
        this.noOfTasks = noOfTasks;
    }

    public long getNoOfTasksCompleted() {
        return noOfTasksCompleted;
    }

    public void setNoOfTasksCompleted(long noOfTasksCompleted) {
        this.noOfTasksCompleted = noOfTasksCompleted;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private long getCountOfCompletedTasks(List<Task> tasks) {
        return tasks.stream().filter(t -> ProjectManagerConstant.STATUS_CLOSED.equals(t.getStatus())).count();
    }
}
