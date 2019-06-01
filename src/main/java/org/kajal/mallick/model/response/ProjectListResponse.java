package org.kajal.mallick.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.model.ProjectDto;

import java.util.ArrayList;
import java.util.List;

public class ProjectListResponse {
    private List<ProjectDto> projects;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public ProjectListResponse() {
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public List<ProjectDto> getProjects() {
        projects = projects == null ? new ArrayList<>() : projects;
        return projects;
    }

    public void setProjects(List<ProjectDto> projects) {
        this.projects = projects;
    }
}
