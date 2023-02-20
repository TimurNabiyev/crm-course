package org.devs.data.dao;

import org.devs.data.entity.Mentor;

import java.util.List;

public interface MentorDao extends BaseDao<Mentor> {

    List<Mentor> findAllByGroupId(Long groupId);

}
