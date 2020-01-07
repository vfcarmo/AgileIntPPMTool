package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.Project;

public interface ProjectService extends CrudService<Project, Long> {

    /**
     * Return a Project by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Project.
     */
    Project findByProjectIdentifier(String projectIdentifier);

    /**
     * Save a Project.
     * @param entity Project.
     * @param username Username
     * @return Project saved.
     */
    Project save(Project entity, String username);

    /**
     * Deletes a Project by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     */
    void deleteByProjectIdentifier(String projectIdentifier);
}
