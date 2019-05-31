package org.kajal.mallick.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task implements Serializable {

    private long taskId;
    private ParentTask parentTask;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private String status;

    public Task() {
    }

    public Task(ParentTask parentTask, String taskName, LocalDate startDate, LocalDate endDate, int priority, String status) {
        this.parentTask = parentTask;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.status = status;
    }

    public Task(long taskId, ParentTask parentTask, String taskName, LocalDate startDate, LocalDate endDate, int priority, String status) {
        this.taskId = taskId;
        this.parentTask = parentTask;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.status = status;
    }

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    public ParentTask getParentTask() {
        return parentTask;
    }

    public void setParentTask(ParentTask parentTask) {
        this.parentTask = parentTask;
    }

    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
        Task task = (Task) o;
        return taskId == task.taskId &&
                priority == task.priority &&
                parentTask.equals(task.parentTask) &&
                taskName.equals(task.taskName) &&
                startDate.equals(task.startDate) &&
                endDate.equals(task.endDate) &&
                status.equals(task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, parentTask, taskName, startDate, endDate, priority, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", parentTask=" + parentTask +
                ", taskName='" + taskName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priority=" + priority +
                ", status='" + status + '\'' +
                '}';
    }
}
