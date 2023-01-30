package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.model.Group;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Group findById(Long id) {
        String query = "SELECT * FROM tb_groups g " +
                "JOIN tb_courses c ON g.course_id = c.id " +
                "WHERE g.id = :id";

        return namedParameterJdbcTemplate.query(query, new MapSqlParameterSource("id", id), (rs, rowNum) -> {
            System.out.println(rowNum);
            Group group = new Group();
            group.setId(rs.getLong("g.id"));
            group.setStarDate(rs.getDate("g.startDate").toLocalDate());
            return group;
        }).get(0);
    }

    @Override
    public Group save(Group group) {
        return null;
    }
}
