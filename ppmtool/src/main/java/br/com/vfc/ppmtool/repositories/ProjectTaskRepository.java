package br.com.vfc.ppmtool.repositories;

import br.com.vfc.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    /**
     * Return a ProjectTask by the given Project Sequence.
     * @param projectSequence Project Sequence.
     * @return Project task.
     */
    Optional<ProjectTask> findByProjectSequence(String projectSequence);

    /**
     * Return a list of ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Project tasks.
     */
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);
}
