package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.Backlog;
import br.com.vfc.ppmtool.domain.ProjectTask;
import br.com.vfc.ppmtool.services.BacklogService;
import br.com.vfc.ppmtool.services.ProjectTaskService;
import br.com.vfc.ppmtool.web.requests.ProjectTaskUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController extends BaseController {

    private BacklogService backlogService;

    private ProjectTaskService projectTaskService;

    @Autowired
    public BacklogController(BacklogService backlogService, ProjectTaskService projectTaskService) {
        this.backlogService = backlogService;
        this.projectTaskService = projectTaskService;
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectBacklog(@PathVariable String projectIdentifier) {

        Backlog savedBacklog = backlogService.findByProjectIdentifier(projectIdentifier);

        return ResponseEntity.ok(savedBacklog.getTasks());
    }

    @PostMapping("/{projectIdentifier}")
    public ResponseEntity<?> addPTtoBacklog(
            @PathVariable String projectIdentifier,
            @Valid @RequestBody ProjectTask request,
            UriComponentsBuilder builder) {

        ProjectTask savedProjectTask = projectTaskService.addProjectTask(projectIdentifier, request);

        URI uri = createURI("/api/backlog/{projectIdentifier}",
                savedProjectTask.getProjectIdentifier(), builder);

        return ResponseEntity.created(uri).body(savedProjectTask);
    }

    @GetMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> getProjectTask(
            @PathVariable String projectIdentifier,
            @PathVariable String projectSequence) {

        ProjectTask savedProjectTask = projectTaskService.findByProjectSequence(projectIdentifier, projectSequence);

        return ResponseEntity.ok(savedProjectTask);
    }

    @PutMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> updateProjectTask(
            @PathVariable String projectIdentifier,
            @PathVariable String projectSequence,
            @Valid @RequestBody ProjectTaskUpdateRequest request) {

        ProjectTask updatedProjectTask = projectTaskService
                .updateProjectTask(projectIdentifier, projectSequence, request);

        return ResponseEntity.ok(updatedProjectTask);
    }

    @DeleteMapping("/{projectIdentifier}/{projectSequence}")
    public ResponseEntity<?> deleteProjectTask(
            @PathVariable String projectIdentifier,
            @PathVariable String projectSequence) {

        projectTaskService.deleteByProjectSequence(projectIdentifier, projectSequence);

        return ResponseEntity.noContent().build();
    }
}
