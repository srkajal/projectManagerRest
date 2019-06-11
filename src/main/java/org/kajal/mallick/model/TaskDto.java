package org.kajal.mallick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.entities.Task;

import java.io.Serializable;

public class TaskDto implements Serializable {
    @JsonProperty("task_id")
    private long taskId;
    @JsonProperty("task_name")
    private String task;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    private int priority;
    private String status;
    @JsonProperty("parent_task")
    private ParentTaskDto parentTaskDto;
    @JsonProperty("project_id")
    private long projectId;
    @JsonProperty("user_id")
    private long userId;

    public TaskDto(Task task) {
        this.taskId = task.getTaskId();
        this.task = task.getTaskName();
        this.startDate = String.valueOf(task.getStartDate());
        this.endDate = String.valueOf(task.getEndDate());
        this.priority = task.getPriority();
        this.status = task.getStatus();
        this.projectId = task.getProject().getProjectId();
        this.userId = task.getUser().getUserId();

        if (task.getParentTask() != null) {
            this.parentTaskDto = new ParentTaskDto(task.getParentTask().getParentId(), task.getParentTask().getParentTaskName());
        }

    }

    public TaskDto() {
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public ParentTaskDto getParentTaskDto() {
        return parentTaskDto;
    }

    public void setParentTaskDto(ParentTaskDto parentTaskDto) {
        this.parentTaskDto = parentTaskDto;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
