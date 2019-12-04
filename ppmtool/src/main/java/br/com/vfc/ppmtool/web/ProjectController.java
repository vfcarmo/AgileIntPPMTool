package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController extends BaseController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<?> listAllProjects() {

        List<Project> projects = projectService.findAll();

        return ResponseEntity.ok(projects);
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project request, UriComponentsBuilder builder, BindingResult result) {

        if (result.hasErrors()) {
            return responseErrors(result);
        }

        Project savedProject = projectService.save(request);
        URI uri = createURI("/api/project/{id}", savedProject.getId(), builder);

        return ResponseEntity.created(uri).body(savedProject);
    }

}
