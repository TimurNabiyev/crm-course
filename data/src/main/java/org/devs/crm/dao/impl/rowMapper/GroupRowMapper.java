package org.devs.crm.dao.impl.rowMapper;

import lombok.RequiredArgsConstructor;
import org.devs.crm.model.Group;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class GroupRowMapper implements RowMapper<Group> {

    private final CourseRowMapper courseRowMapper;

    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Group.builder()
                .id(rs.getLong("id"))
                .startDate(rs.getDate("start_date").toLocalDate())
                .course(courseRowMapper.mapRow(rs, rowNum))
                .build();
    }
}
