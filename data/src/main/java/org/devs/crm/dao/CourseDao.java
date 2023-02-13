package org.devs.crm.dao;

import org.devs.crm.entity.Course;

import java.util.Optional;

public interface CourseDao extends BaseDao<Course> {
    Optional<Course> findByGroupId(Long id);
}
