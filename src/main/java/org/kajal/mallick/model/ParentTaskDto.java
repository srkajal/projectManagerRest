package org.kajal.mallick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.entities.ParentTask;

public class ParentTaskDto {
    @JsonProperty("parent_id")
    private long parentId;
    @JsonProperty("parent_task_name")
    private String parentTask;

    public ParentTaskDto(ParentTask parentTask) {
        this.parentId = parentTask.getParentId();
        this.parentTask = parentTask.getParentTaskName();
    }


    public ParentTaskDto(long parentId, String parentTask) {
        this.parentId = parentId;
        this.parentTask = parentTask;
    }

    public ParentTaskDto() {
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }
}
