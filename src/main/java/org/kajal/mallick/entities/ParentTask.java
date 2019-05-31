package org.kajal.mallick.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "parent_task")
public class ParentTask implements Serializable {
    private long parentId;
    private String parentTaskName;

    public ParentTask() {
    }

    public ParentTask(long parentId) {
        this.parentId = parentId;
    }

    public ParentTask(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    @Id
    @Column(name = "parent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Column(name = "parent_task_name")
    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentTask that = (ParentTask) o;
        return parentId == that.parentId &&
                parentTaskName.equals(that.parentTaskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, parentTaskName);
    }

    @Override
    public String toString() {
        return "ParentTask{" +
                "parentId=" + parentId +
                ", parentTask='" + parentTaskName + '\'' +
                '}';
    }
}
