package org.devs.data.dao;

import org.devs.data.dao.exception.InvalidIdException;
import org.devs.data.dao.impl.CourseDaoImpl;
import org.devs.data.dao.impl.query.CourseQuery;
import org.devs.data.dao.impl.rowmapper.CourseRowMapper;
import org.devs.data.entity.Course;


import java.util.Optional;
/**
 * <p>Extension of {@link BaseDao} interface and declares {@link Course} specific methods</p>
 *
 * @see BaseDao
 * @see Course
 * @see CourseDaoImpl
 * @see CourseRowMapper
 * @see CourseQuery
 */
public interface CourseDao extends BaseDao<Course> {

    /**
     * <p>Retrieves an entity by group id</p>
     * @param id must not be null and less than or equal to zero
     * @return entity with assigned id wrapped in {@link Optional} or {@link Optional#empty()} if none found
     * @throws InvalidIdException if id is null, less than or equal to zero
     */
    Optional<Course> findByGroupId(Long id);
}
