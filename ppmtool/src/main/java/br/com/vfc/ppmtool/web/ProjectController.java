package br.com.vfc.ppmtool.web;

import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.services.ProjectService;
import br.com.vfc.ppmtool.web.errors.MessageErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project request, BindingResult result) {

        if (result.hasErrors()) {
            MessageErrors errors = new MessageErrors(result.getFieldErrors());

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Project savedProject = projectService.save(request);

        return ResponseEntity.created(URI.create("/api/project/" + savedProject.getId())).body(savedProject);
    }
}
