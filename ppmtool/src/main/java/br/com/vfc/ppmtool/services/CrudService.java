package br.com.vfc.ppmtool.services;

import br.com.vfc.ppmtool.domain.BaseEntity;
import br.com.vfc.ppmtool.domain.Project;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends BaseEntity, ID> {


    /**
     * Return all records of the Entity.
     * @return Records of the Entity.
     */
    List<T> findAll();

    /**
     * Return the entity with the given id.
     * @param id Identifier of the Entity.
     * @return Entity.
     */
    T findById(ID id);

    /**
     * Save the entity.
     * @param entity Entity.
     * @return Entity saved.
     */
    T save(T entity);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void deleteById(ID id);
}
