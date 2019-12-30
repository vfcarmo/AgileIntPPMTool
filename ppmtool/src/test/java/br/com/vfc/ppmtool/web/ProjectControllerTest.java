package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.exceptions.CustomResponseEntityExceptionHandler;
import br.com.vfc.ppmtool.exceptions.ResourceNotFoundException;
import br.com.vfc.ppmtool.fixture.ProjectFixture;
import br.com.vfc.ppmtool.services.ProjectService;
import br.com.vfc.ppmtool.web.requests.ProjectUpdateRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @InjectMocks
    private CustomResponseEntityExceptionHandler customResponseEntityExceptionHandler;

    @BeforeEach
    void setUp() {
        standaloneSetup(projectController, customResponseEntityExceptionHandler);
        Mockito.lenient().when(projectService.findAll()).thenReturn(ProjectFixture.createProjectList());
        Mockito.lenient().when(projectService.findByProjectIdentifier(anyString()))
                .thenReturn(ProjectFixture.createProject());
        Mockito.lenient().when(projectService.save(any(Project.class))).thenReturn(ProjectFixture.createProject());
    }

    @Test
    @DisplayName("When GET Project then respond with status OK")
    void whenGetProjectRequest_Then_RespondWithStatusOk() {
        given()
            .log().ifValidationFails()
        .when()
            .get("api/project")
        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("When GET Project with no records then respond with status OK and empty body")
    void whenGetProjectRequest_Then_RespondWithStatusOkAndEmptyBody() {
        Mockito.when(projectService.findAll()).thenReturn(Collections.emptyList());

        given()
            .log().ifValidationFails()
        .when()
            .get("api/project")
        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .body(is(equalTo("[]")));
    }

    @Test
    @DisplayName("Given matching project identifier when GET Project then respond with status OK")
    void givenMatchingProjectWhenGetProjectRequest_Then_RespondWithStatusOk() {

        String noMatchingIdentifier = "matchingIdentifier";

        given()
            .log().ifValidationFails()
        .when()
            .get("api/project/{projectIdentifier}", noMatchingIdentifier)
        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Given no matching project identifier when GET Project then respond with status not found")
    void givenNoMatchingProjectWhenGetProjectRequest_Then_RespondWithStatusNotFound() {

        String noMatchingIdentifier = "noMatchingIdentifier";
        Mockito.when(projectService.findByProjectIdentifier(noMatchingIdentifier))
                .thenThrow(new ResourceNotFoundException(noMatchingIdentifier));

        given()
            .log().ifValidationFails()
        .when()
            .get("api/project/{projectIdentifier}", noMatchingIdentifier)
        .then()
            .log().all()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Given a project with all fields filled when POST Project then respond with status created")
    void givenProjectWithAllFieldsWhenPostProjectRequest_Then_RespondWithStatusCreated() {

        Project project = ProjectFixture.createProject();

        given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(project)
        .when()
            .post("api/project")
        .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Given a project with no name when POST Project then respond with status bad request")
    void givenProjectWithNoNameWhenPostProjectRequest_Then_RespondWithStatusBadRequest() {

        Project project = ProjectFixture.createProjectWithNoName();

        given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(project)
                .when()
                .post("api/project")
                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Given a project with no identifier when POST Project then respond with status bad request")
    void givenProjectWithNoIdentifierWhenPostProjectRequest_Then_RespondWithStatusBadRequest() {

        Project project = ProjectFixture.createProjectWithNoIdentifier();

        given()
            .log().ifValidationFails()
            .contentType(ContentType.JSON)
            .body(project)
        .when()
            .post("api/project")
        .then()
            .log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Given a project with no description when POST Project then respond with status bad request")
    void givenProjectWithNoDescriptionWhenPostProjectRequest_Then_RespondWithStatusBadRequest() {

        Project project = ProjectFixture.createProjectWithNoDescription();

        given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(project)
                .when()
                .post("api/project")
                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Given a project with all fields filled when PUT Project then respond with status ok")
    void givenProjectWithAllFieldsWhenPutProjectRequest_Then_RespondWithStatusCreated() {

        ProjectUpdateRequest request = ProjectFixture.createProjectUpdateRequest();

        given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put("api/project/{projectIdentifier}", "ABC12")
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
    }
}