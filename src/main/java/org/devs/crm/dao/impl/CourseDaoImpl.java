package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class CourseDaoImpl implements CourseDao {

    @Autowired
    public DataSource dataSource;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Course findById(Long id) {
        String query = "SELECT * FROM tb_courses WHERE id = :id";

        return namedParameterJdbcTemplate.query(query, new MapSqlParameterSource("id", id), (rs, rowNum) -> {
            System.out.println(rowNum);
            Course course = new Course();
            course.setId(rs.getLong("id"));
            course.setName(rs.getString("name"));
            course.setSubject(rs.getString("subject"));
            course.setCourseDurationInMonth(rs.getInt("course_duration_in_month"));
            course.setLessonDuration(rs.getTime("lesson_duration").toLocalTime());
            course.setCoursePrice(rs.getBigDecimal("course_price"));
            return course;
        }).get(0);
    }

    @Override
    public Course save(Course course) {
        String query = "" +
                "INSERT INTO tb_courses(name, subject, course_duration_in_month, lesson_duration, course_price) " +
                "VALUES(:name, :subject, :course_duration_in_month, :lesson_duration, :course_price)";

        KeyHolder holder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("name", course.getName())
                .addValue("subject", course.getSubject())
                .addValue("courseDurationInMonth", course.getCourseDurationInMonth())
                .addValue("lessonDuration", course.getLessonDuration())
                .addValue("coursePrice", course.getCoursePrice());

        namedParameterJdbcTemplate.update(query, source, holder, new String[]{"id"});

        course.setId(holder.getKey().longValue());
        return course;
    }
}
