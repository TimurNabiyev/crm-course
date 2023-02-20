package org.devs.data.dao;

import org.devs.data.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {

    List<Student> findAllByGroupId(Long groupId);

}
