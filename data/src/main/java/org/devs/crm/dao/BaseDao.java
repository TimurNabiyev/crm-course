package org.devs.crm.dao;

import org.devs.crm.dao.exception.EntityNotFoundException;
import org.devs.crm.dao.exception.InvalidIdException;

import java.util.Optional;

/**
 * <p>Interface for generic CREATE and READ operations
 *  The purpose of this class is to provide core, required methods for the child dao classes.</p>
 *
 * <ul>
 *     <li>
 *         <b>findById</b> - retrieves an entity by its id
 *     </li>
 *     <li>
 *         <b>save</b> - saves given entity and retrieves it with generated id
 *     </li>
 * </ul>
 *
 * @param <T> specified by subclasses. Required entity type classes
 *
 * @see GroupDao
 * @see CourseDao
 * @see MentorDao
 * @see StudentDao
 */

public interface BaseDao<T> {

    /**
     * <p>Retrieves an entity by its id</p>
     * @param id must not be null and less than or equal to zero
     * @return entity with assigned id wrapped in {@link Optional} or {@link Optional#empty()} if none found
     * @throws InvalidIdException if id is null, less than or equal to zero
     */
    Optional<T> findById(Long id);

    /**
     * <p>Saves given entity and retrieves the same entity with generated id.</p>
     *
     * @param entity must not be null and follow the rules of entity states
     * @return saved entity with assigned id. Will never be null.
     * @throws EntityNotFoundException in case if it has included entities which not found in database
     */
    T save(T entity);

}
