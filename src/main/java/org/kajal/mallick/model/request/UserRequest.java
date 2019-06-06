package org.kajal.mallick.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRequest {
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("project_id")
    private long projectId;

    @JsonProperty("task_id")
    private long taskId;

    @NotNull(message = "First Name should not be Blank")
    @NotEmpty(message = "First Name should not be Blank")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last Name should not be Blank")
    @NotEmpty(message = "Last Name should not be Blank")
    @JsonProperty("last_name")
    private String lastName;

    @Min(value = 1, message = "EmployeeId should be greater than 0")
    @JsonProperty("employee_id")
    private int employeeId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
