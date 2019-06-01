package org.kajal.mallick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.entities.Project;

import java.io.Serializable;

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

    public ProjectDto() {
    }

    public ProjectDto(long projectId, String projectName, String startDate, String endDate, int priority) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    public ProjectDto(Project project) {
        this(project.getProjectId(), project.getProjectName(), String.valueOf(project.getStartDate()), String.valueOf(project.getEndDate()), project.getPriority());
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
}
