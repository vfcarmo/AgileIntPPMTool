package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.ProjectTask;

public interface ProjectTaskService extends CrudService<ProjectTask, Long> {

    /**
     * Return a ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return ProjectTask.
     */
    ProjectTask findByProjectIdentifier(String projectIdentifier);

    /**
     * Deletes a ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     */
    void deleteByProjectIdentifier(String projectIdentifier);
}
