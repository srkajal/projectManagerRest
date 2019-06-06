package org.kajal.mallick.model.response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.model.ProjectDto;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class ProjectListResponseTest {
    private final ProjectDto project;
    private final BaseResponse baseResponse;
    @InjectMocks
    private ProjectListResponse projectListResponse;

    public ProjectListResponseTest(ProjectDto project, BaseResponse baseResponse) {
        this.project = project;
        this.baseResponse = baseResponse;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with PROJECTS={0}, BASE_RESPONSE={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new ProjectDto(1l, "project -1", "01-01-2019", "01-02-2019", 1, 2), new BaseResponse("SUCCESS", 200, "Successfully fetch")},
                {new ProjectDto(2l, "project -2", "01-01-2019", "01-02-2019", 2, 3), new BaseResponse("SUCCESS", 200, "Successfully fetch")}
        });
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getBaseResponse() {
        projectListResponse.setBaseResponse(baseResponse);

        assertEquals(baseResponse.getCode(), projectListResponse.getBaseResponse().getCode());
    }

    @Test
    public void getProjects() {

        projectListResponse.setProjects(Collections.singletonList(project));

        assertEquals(project.getProjectName(), projectListResponse.getProjects().get(0).getProjectName());
    }
}