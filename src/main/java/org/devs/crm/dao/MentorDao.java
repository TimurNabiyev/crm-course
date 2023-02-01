package org.devs.crm.dao;

import org.devs.crm.model.Mentor;

import java.util.List;

public interface MentorDao extends BaseDao<Mentor> {

    List<Mentor> findAllByGroupId(Long groupId);

}
