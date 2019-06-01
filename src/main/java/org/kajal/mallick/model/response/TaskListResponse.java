package org.kajal.mallick.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.model.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class TaskListResponse {
    private List<TaskDto> tasks;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public TaskListResponse() {
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public List<TaskDto> getTasks() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
