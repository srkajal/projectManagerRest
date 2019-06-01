package org.kajal.mallick.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.model.ParentTaskDto;

import java.util.ArrayList;
import java.util.List;

public class ParentTaskListResponse {
    @JsonProperty("parent_tasks")
    private List<ParentTaskDto> parentTasks;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public ParentTaskListResponse() {
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public List<ParentTaskDto> getParentTasks() {
        if (parentTasks == null) {
            parentTasks = new ArrayList<>();
        }
        return parentTasks;
    }

    public void setParentTasks(List<ParentTaskDto> parentTasks) {
        this.parentTasks = parentTasks;
    }
}
