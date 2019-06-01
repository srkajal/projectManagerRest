package org.kajal.mallick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.entities.User;

public class UserDto {
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("employee_id")
    private int employeeId;
    @JsonProperty("project")
    private ProjectDto project;
    @JsonProperty("task")
    private TaskDto task;

    public UserDto() {
    }

    public UserDto(long userId, String firstName, String lastName, int employeeId, ProjectDto project, TaskDto task) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.project = project;
        this.task = task;
    }

    public UserDto(User user) {
        this(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmployeeId(), user.getProject() != null ? new ProjectDto(user.getProject()) : null, user.getTask() != null ? new TaskDto(user.getTask()) : null);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public TaskDto getTask() {
        return task;
    }

    public void setTask(TaskDto task) {
        this.task = task;
    }
}
