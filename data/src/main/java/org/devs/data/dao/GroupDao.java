package org.devs.data.dao;

import org.devs.data.dao.impl.GroupDaoImpl;
import org.devs.data.dao.impl.query.GroupQuery;
import org.devs.data.dao.impl.rowmapper.GroupRowMapper;
import org.devs.data.entity.Group;

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
