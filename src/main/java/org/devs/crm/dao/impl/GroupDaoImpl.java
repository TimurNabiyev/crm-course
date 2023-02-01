package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.impl.query.GroupQuery;
import org.devs.crm.dao.impl.rowMapper.GroupRowMapper;
import org.devs.crm.model.Group;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GroupRowMapper groupRowMapper;
    private final StudentDao studentDao;
    private final MentorDao mentorDao;

    @Override
    public Optional<Group> findById(Long id) {
        return namedParameterJdbcTemplate.query(GroupQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), groupRowMapper).stream().findFirst();
    }

    @Override
    public Group save(Group group) {
        return null;
    }
}
