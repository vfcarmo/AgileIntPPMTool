package br.com.vfc.ppmtool.repositories;

import br.com.vfc.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    /**
     * Return a Project by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Project.
     */
    Optional<Project> findByProjectIdentifier(String projectIdentifier);
}
