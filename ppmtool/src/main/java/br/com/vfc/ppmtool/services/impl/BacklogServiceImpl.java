package br.com.vfc.ppmtool.services.impl;

import br.com.vfc.ppmtool.domain.Backlog;
import br.com.vfc.ppmtool.domain.Project;
import br.com.vfc.ppmtool.exceptions.ResourceNotFoundException;
import br.com.vfc.ppmtool.repositories.BacklogRepository;
import br.com.vfc.ppmtool.services.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BacklogServiceImpl implements BacklogService {

    private BacklogRepository repository;

    @Autowired
    public BacklogServiceImpl(BacklogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Backlog> findAll() {

        Iterable<Backlog> backlogIterable = repository.findAll();
        return StreamSupport.stream(backlogIterable.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Backlog findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Backlog findByProjectIdentifier(String projectIdentifier) {
        return repository.findByProjectIdentifier(projectIdentifier.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException(projectIdentifier));
    }

    @Override
    public Backlog save(Backlog entity) {
        Backlog savedBacklog;
        try {
            savedBacklog = repository.save(entity);
        } catch (Exception e) {
            savedBacklog = null;
        }
        return savedBacklog;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByProjectIdentifier(String projectIdentifier) {

        Backlog savedBacklog = findByProjectIdentifier(projectIdentifier);

        repository.delete(savedBacklog);
    }
}
