package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.Backlog;
import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.services.BacklogService;
import br.com.vfc.ppmtool.services.ProjectService;
import br.com.vfc.ppmtool.services.ProjectTaskService;
import br.com.vfc.ppmtool.web.requests.ProjectUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController extends BaseController {

    private ProjectService projectService;

    private BacklogService backlogService;

    private ProjectTaskService projectTaskService;

    @Autowired
    public ProjectController(ProjectService projectService, BacklogService backlogService, ProjectTaskService projectTaskService) {
        this.projectService = projectService;
        this.backlogService = backlogService;
        this.projectTaskService = projectTaskService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllProjects() {

        List<Project> projects = projectService.findAll();

        return ResponseEntity.ok(projects);
    }

    @GetMapping(value = "/{projectIdentifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findProject(@PathVariable String projectIdentifier) {

        Project savedProject = projectService.findByProjectIdentifier(projectIdentifier);

        return ResponseEntity.ok(savedProject);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project request,
                                              UriComponentsBuilder builder) {

        Project savedProject = projectService.save(request);
        URI uri = createURI("/api/project/{projectIdentifier}",
                savedProject.getProjectIdentifier(), builder);

        return ResponseEntity.created(uri).body(savedProject);
    }

    @PutMapping(value = "/{projectIdentifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProject(@PathVariable String projectIdentifier,
                                           @Valid @RequestBody ProjectUpdateRequest request) {
        Project savedProject = projectService.findByProjectIdentifier(projectIdentifier);
        BeanUtils.copyProperties(request, savedProject);

        Project updatedProject = projectService.save(savedProject);

        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping(value = "/{projectIdentifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProjectByProjectIdentifier(@PathVariable String projectIdentifier) {

        projectService.deleteByProjectIdentifier(projectIdentifier);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectIdentifier}/backlog")
    public ResponseEntity<?> getProjectBacklog(@PathVariable String projectIdentifier) {

        Backlog savedBacklog = backlogService.findByProjectIdentifier(projectIdentifier);
        return ResponseEntity.ok(savedBacklog.getTasks());
    }
}
