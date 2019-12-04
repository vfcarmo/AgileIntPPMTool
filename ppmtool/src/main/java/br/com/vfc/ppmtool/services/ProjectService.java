package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.Project;

public interface ProjectService extends CrudService<Project, Long> {

    /**
     * Return the Project with the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Project.
     */
    Project findByProjectIdentifier(String projectIdentifier);
}
