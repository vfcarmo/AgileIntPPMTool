package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.ProjectTask;
import br.com.vfc.ppmtool.services.BacklogService;
import br.com.vfc.ppmtool.services.ProjectTaskService;
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
}
