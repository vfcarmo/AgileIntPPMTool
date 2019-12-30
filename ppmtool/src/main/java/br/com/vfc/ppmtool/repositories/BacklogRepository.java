package br.com.vfc.ppmtool.repositories;

import br.com.vfc.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    /**
     * Return a Backlog by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Backlog.
     */
    Optional<Backlog> findByProjectIdentifier(String projectIdentifier);
}
