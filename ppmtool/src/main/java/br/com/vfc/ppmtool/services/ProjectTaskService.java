package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.ProjectTask;
import br.com.vfc.ppmtool.web.requests.ProjectTaskUpdateRequest;

import java.util.List;

public interface ProjectTaskService extends CrudService<ProjectTask, Long> {

    /**
     * Return a ProjectTask by the given Project Sequence.
     *
     * @param projectIdentifier Project Identifier.
     * @param projectSequence Project Sequence.
     * @return Project task.
     */
    ProjectTask findByProjectSequence(String projectIdentifier, String projectSequence);

    /**
     * Return a list of ProjectTask by the given Project Identifier.
     * @param projectIdentifier Project Identifier.
     * @return Project tasks.
     */
    List<ProjectTask> findByProjectIdentifier(String projectIdentifier);

    /**
     * Deletes a ProjectTask by the given Project Identifier and Project Sequence.
     * @param projectIdentifier Project Identifier.
     * @param projectSequence Project Sequence.
     */
    void deleteByProjectSequence(String projectIdentifier, String projectSequence);

    /**
     * Add a ProjectTask.
     * @param projectIdentifier Project Identifier.
     * @param entity ProjectTask.
     * @return Saved ProjectTask.
     */
    ProjectTask addProjectTask(String projectIdentifier, ProjectTask entity);

    /**
     * Updates a ProjectTask.
     * @param projectIdentifier Project Identifier.
     * @param projectSequence Project Sequence.
     * @param entity ProjectTask.
     * @return Updated Project Task.
     */
    ProjectTask updateProjectTask(String projectIdentifier, String projectSequence, ProjectTaskUpdateRequest entity);
}
