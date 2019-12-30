package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.Backlog;

public interface BacklogService extends CrudService<Backlog, Long> {

    /**
     * Return a Backlog by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Backlog.
     */
    Backlog findByProjectIdentifier(String projectIdentifier);

    /**
     * Deletes a Backlog by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     */
    void deleteByProjectIdentifier(String projectIdentifier);
}
