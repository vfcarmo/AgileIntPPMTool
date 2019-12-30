package br.com.vfc.ppmtool.services.impl;

import br.com.vfc.ppmtool.domain.ProjectTask;
import br.com.vfc.ppmtool.exceptions.ResourceNotFoundException;
import br.com.vfc.ppmtool.repositories.ProjectTaskRepository;
import br.com.vfc.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private ProjectTaskRepository repository;

    @Autowired
    public ProjectTaskServiceImpl(ProjectTaskRepository repository) {
        this.repository = repository;
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
    public ProjectTask findByProjectIdentifier(String projectIdentifier) {
        return repository.findByProjectIdentifier(projectIdentifier.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException(projectIdentifier));
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
    public void deleteByProjectIdentifier(String projectIdentifier) {

        ProjectTask savedProjectTask = findByProjectIdentifier(projectIdentifier);

        repository.delete(savedProjectTask);
    }
}
