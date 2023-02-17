package org.devs.crm.dao;

import org.devs.crm.dao.exception.InvalidIdException;
import org.devs.crm.dao.impl.CourseDaoImpl;
import org.devs.crm.dao.impl.query.CourseQuery;
import org.devs.crm.dao.impl.rowMapper.CourseRowMapper;
import org.devs.crm.entity.Course;


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
