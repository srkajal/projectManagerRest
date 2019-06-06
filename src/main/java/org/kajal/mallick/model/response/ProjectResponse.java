package org.kajal.mallick.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.model.ProjectDto;

public class ProjectResponse {
    @JsonProperty("project")
    private ProjectDto projectDto;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public ProjectResponse() {
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }
}
