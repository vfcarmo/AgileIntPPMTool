package br.com.vfc.ppmtool.repositories;

import br.com.vfc.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    /**
     * Return a ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return ProjectTask.
     */
    Optional<ProjectTask> findByProjectIdentifier(String projectIdentifier);
}
