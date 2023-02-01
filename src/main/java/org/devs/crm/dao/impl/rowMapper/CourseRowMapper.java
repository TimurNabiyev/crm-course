package org.devs.crm.dao.impl.rowMapper;

import org.devs.crm.model.Course;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Course.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .subject(rs.getString("subject"))
                .courseDurationInMonth(rs.getInt("course_duration_in_month"))
                .lessonDuration(rs.getTime("lesson_duration").toLocalTime())
                .coursePrice(rs.getBigDecimal("course_price"))
                .build();
    }
}
