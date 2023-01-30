package org.devs.crm.dao.impl.rowMapper;

import org.devs.crm.model.Course;
import org.devs.crm.model.Group;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GroupRowMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Group.builder()
                .id(rs.getLong("id"))
                .starDate(rs.getDate("start_date").toLocalDate())
                .course(Course.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .coursePrice(rs.getBigDecimal("course_price"))
                        .subject(rs.getString("subject"))
                        .lessonDuration(rs.getTime("lesson_duration").toLocalTime())
                        .courseDurationInMonth(rs.getInt("course_duration_in_month"))
                        .build())
                .build();
    }
}
