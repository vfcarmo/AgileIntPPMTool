package br.com.vfc.ppmtool.services.impl;

import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.exceptions.ProjectConflictException;
import br.com.vfc.ppmtool.exceptions.ProjectNotFoundException;
import br.com.vfc.ppmtool.repositories.ProjectRepository;
import br.com.vfc.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {

        Iterable<Project> projectIterable = projectRepository.findAll();

        return StreamSupport.stream(projectIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                new ProjectNotFoundException(id));
    }

    @Override
    public Project findByProjectIdentifier(String projectIdentifier) {
        return projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase()).orElseThrow(() ->
                new ProjectNotFoundException(projectIdentifier));
    }

    @Override
    public Project save(Project entity) {
        Project savedProject;
        try {
            entity.setProjectIdentifier(entity.getProjectIdentifier().toUpperCase());
            savedProject = projectRepository.save(entity);
        } catch (Exception e) {
            throw new ProjectConflictException(entity.getProjectIdentifier());
        }
        return savedProject;
    }

    @Override
    public void deleteById(Long id) {
        this.projectRepository.deleteById(id);
    }

    @Override
    public void deleteByProjectIdentifier(String projectIdentifier) {

        Project savedProject = findByProjectIdentifier(projectIdentifier);

        this.projectRepository.delete(savedProject);
    }
}
