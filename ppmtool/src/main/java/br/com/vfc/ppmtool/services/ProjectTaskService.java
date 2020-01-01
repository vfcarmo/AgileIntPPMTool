package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskService extends CrudService<ProjectTask, Long> {

    /**
     * Return a ProjectTask by the given Project Sequence.
     * @param projectSequence Project Sequence.
     * @return Project task.
     */
    ProjectTask findByProjectSequence(String projectSequence);

    /**
     * Return a list of ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Project tasks.
     */
    List<ProjectTask> findByProjectIdentifier(String projectIdentifier);

    /**
     * Deletes a ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     */
    void deleteByProjectIdentifier(String projectIdentifier);

    /**
     * Add a ProjectTask.
     * @param projectIdentifier Project Identifier.
     * @param entity ProjectTask.
     * @return Saved ProjectTask.
     */
    ProjectTask addProjectTask(String projectIdentifier, ProjectTask entity);
}
