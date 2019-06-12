package org.kajal.mallick.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CreateTaskRequest {

    @JsonProperty("parent_id")
    private long parentId;

    @Min(value = 1, message = "Project Id should be greater than 0")
    @JsonProperty("project_id")
    private long projectId;

    @NotNull(message = "Task should not be Blank")
    @NotEmpty(message = "Task should not be Blank")
    @JsonProperty("task_name")
    private String taskName;

    @NotNull(message = "Start date should not be Blank")
    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date should not be Blank")
    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Min(value = 1, message = "Priority should be greater than 0")
    private int priority;

    @JsonProperty("user_id")
    @Min(value = 1, message = "User id should be greater than 0")
    private long userId;

    public CreateTaskRequest() {
    }

    public CreateTaskRequest(long parentId, @Min(value = 1, message = "Project Id should be greater than 0") long projectId, @NotNull(message = "Task should not be Blank") @NotEmpty(message = "Task should not be Blank") String taskName, @NotNull(message = "Start date should not be Blank") LocalDate startDate, @NotNull(message = "End date should not be Blank") LocalDate endDate, @Min(value = 1, message = "Priority should be greater than 0") int priority) {
        this.parentId = parentId;
        this.projectId = projectId;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
