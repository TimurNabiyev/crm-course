package org.devs.crm.dao;

import org.devs.crm.dao.impl.GroupDaoImpl;
import org.devs.crm.dao.impl.query.GroupQuery;
import org.devs.crm.dao.impl.rowMapper.GroupRowMapper;
import org.devs.crm.model.Group;

import java.util.Optional;

/**
 * <p>Extension of {@link BaseDao} interface and declares {@link Group} specific methods</p>
 *
 * @see BaseDao
 * @see Group
 * @see GroupDaoImpl
 * @see GroupRowMapper
 * @see GroupQuery
 */

public interface GroupDao extends BaseDao<Group> {
    Optional<Group> findByGroupName(String name);
}
