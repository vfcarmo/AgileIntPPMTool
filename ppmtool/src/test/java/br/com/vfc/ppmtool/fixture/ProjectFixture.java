package br.com.vfc.ppmtool.fixture;

import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.web.requests.ProjectUpdateRequest;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProjectFixture {

    public static Project createProjectWithNoName() {
        Project project = createProject();
        project.setProjectName(null);
        return project;
    }

    public static Project createProjectWithNoIdentifier() {
        Project project = createProject();
        project.setProjectIdentifier(null);
        return project;
    }

    public static Project createProjectWithNoDescription() {
        Project project = createProject();
        project.setDescription(null);
        return project;
    }

    public static Project createProject() {

        Project project = new Project();
        project.setId(1L);
        project.setProjectName("Project ABC");
        project.setProjectIdentifier("ABC12");
        project.setDescription("Description Project ABC");
        Date startDate = java.sql.Date.valueOf(LocalDate.of(2019, 12, 17));
        Date endDate = java.sql.Date.valueOf(LocalDate.of(2019, 12, 23));
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        return project;
    }

    public static ProjectUpdateRequest createProjectUpdateRequest() {
        ProjectUpdateRequest projectUpdateRequest = new ProjectUpdateRequest();
        BeanUtils.copyProperties(createProject(), projectUpdateRequest);
        return projectUpdateRequest;
    }

    public static List<Project> createProjectList() {
        return Collections.singletonList(createProject());
    }
}
