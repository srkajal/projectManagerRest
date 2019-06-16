package org.kajal.mallick.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    private long projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private List<Task> tasks;
    private User user;
    private String status;

    public Project() {
    }

    public Project(long projectId) {
        this.projectId = projectId;
    }

    public Project(String projectName, LocalDate startDate, LocalDate endDate, int priority) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
    }

    public Project(String projectName, LocalDate startDate, LocalDate endDate, int priority, String status) {
        this(projectName, startDate, endDate, priority);
        this.status = status;
    }

    public Project(long projectId, String projectName, LocalDate startDate, LocalDate endDate, int priority) {
        this(projectName, startDate, endDate, priority);
        this.projectId = projectId;
    }

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "start_date")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId &&
                priority == project.priority &&
                projectName.equals(project.projectName) &&
                startDate.equals(project.startDate) &&
                endDate.equals(project.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, startDate, endDate, priority);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priority=" + priority +
                ", tasks=" + tasks +
                ", user=" + user +
                ", status='" + status + '\'' +
                '}';
    }
}
