package org.kajal.mallick.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParentTaskRequest {
    @JsonProperty("parent_id")
    private long parentId;

    @NotNull(message = "Parent Task name should not be Blank")
    @NotEmpty(message = "Parent Task name should not be Blank")
    @JsonProperty("parent_task_name")
    private String parentTaskName;


    public ParentTaskRequest() {
    }

    public ParentTaskRequest(@NotNull(message = "Parent Task name should not be Blank") @NotEmpty(message = "Parent Task name should not be Blank") String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }

    public ParentTaskRequest(long parentId, @NotNull(message = "Parent Task name should not be Blank") @NotEmpty(message = "Parent Task name should not be Blank") String parentTaskName) {
        this.parentId = parentId;
        this.parentTaskName = parentTaskName;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getParentTaskName() {
        return parentTaskName;
    }

    public void setParentTaskName(String parentTaskName) {
        this.parentTaskName = parentTaskName;
    }
}
