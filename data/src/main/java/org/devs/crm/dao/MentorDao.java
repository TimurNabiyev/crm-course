package org.devs.crm.dao;

import org.devs.crm.dao.exception.InvalidIdException;
import org.devs.crm.dao.impl.MentorDaoImpl;
import org.devs.crm.dao.impl.query.MentorQuery;
import org.devs.crm.dao.impl.rowMapper.MentorRowMapper;
import org.devs.crm.model.Mentor;

import java.util.List;
import java.util.Optional;

/**
 * <p>Extension of {@link BaseDao} interface and declares {@link Mentor} specific methods</p>
 *
 * <ul>
 *     <li>
 *         <b>findAllByGroupId</b> - retrieves an entity by its id
 *     </li>
 * </ul>
 *
 * @see BaseDao
 * @see Mentor
 * @see MentorDaoImpl
 * @see MentorRowMapper
 * @see MentorQuery
 */

public interface MentorDao extends BaseDao<Mentor> {

    /**
     * <p>Retrieves an entity by its id</p>
     * @param groupId must not be null and less than or equal to zero
     * @return entity with assigned id wrapped in {@link Optional} or {@link Optional#empty()} if none found
     * @throws InvalidIdException if id is null, less than or equal to zero
     */
    List<Mentor> findAllByGroupId(Long groupId);

}
