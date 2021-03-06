package br.com.vfc.ppmtool.services.impl;

import br.com.vfc.ppmtool.domain.Backlog;
import br.com.vfc.ppmtool.domain.ProjectTask;
import br.com.vfc.ppmtool.exceptions.ResourceNotFoundException;
import br.com.vfc.ppmtool.repositories.ProjectTaskRepository;
import br.com.vfc.ppmtool.services.BacklogService;
import br.com.vfc.ppmtool.services.ProjectTaskService;
import br.com.vfc.ppmtool.web.requests.ProjectTaskUpdateRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private ProjectTaskRepository repository;

    private BacklogService backlogService;

    @Autowired
    public ProjectTaskServiceImpl(ProjectTaskRepository repository, BacklogService backlogService) {
        this.repository = repository;
        this.backlogService = backlogService;
    }

    @Override
    public List<ProjectTask> findAll() {

        Iterable<ProjectTask> projectTaskIterable = repository.findAll();

        return StreamSupport.stream(projectTaskIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public ProjectTask findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public ProjectTask findByProjectSequence(String projectIdentifier, String projectSequence) {
        ProjectTask savedProjectTask = repository.findByProjectSequence(projectSequence)
                .orElseThrow(() -> new ResourceNotFoundException(projectSequence));

        if (!savedProjectTask.getProjectIdentifier().equals(projectIdentifier)) {
            throw new ResourceNotFoundException(projectIdentifier);
        }
        return savedProjectTask;
    }

    @Override
    public List<ProjectTask> findByProjectIdentifier(String projectIdentifier) {
        return repository.findByProjectIdentifierOrderByPriority(projectIdentifier.toUpperCase());
    }

    @Override
    public ProjectTask save(ProjectTask entity) {
        ProjectTask savedProjectTask;

        try {
            savedProjectTask = repository.save(entity);
        } catch (Exception e) {
            savedProjectTask = null;
        }
        return savedProjectTask;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByProjectSequence(String projectIdentifier, String projectSequence) {

        ProjectTask savedProjectTask = findByProjectSequence(projectIdentifier, projectSequence);

        this.repository.delete(savedProjectTask);
    }

    @Override
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask entity) {

        Backlog savedBacklog = backlogService.findByProjectIdentifier(projectIdentifier);
        entity.setBacklog(savedBacklog);

        Integer backlogSequence = savedBacklog.getPTSequence();
        backlogSequence++;
        savedBacklog.setPTSequence(backlogSequence);
        entity.setProjectSequence(String.format("%s-%s", projectIdentifier, backlogSequence));
        entity.setProjectIdentifier(projectIdentifier);

        if (entity.getPriority() == null || entity.getPriority() == 0) {
            entity.setPriority(3);
        }

        if (entity.getStatus() == null || "".equals(entity.getStatus().trim())) {
            entity.setStatus("TO-DO");
        }

        return repository.save(entity);
    }

    @Override
    public ProjectTask updateProjectTask(String projectIdentifier, String projectSequence, ProjectTaskUpdateRequest entity) {

        ProjectTask savedProjectTask = findByProjectSequence(projectIdentifier, projectSequence);
        BeanUtils.copyProperties(entity, savedProjectTask);


        ProjectTask updatedProjectTask = repository.save(savedProjectTask);

        return updatedProjectTask;
    }


}
