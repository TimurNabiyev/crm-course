package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.model.Course;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CourseDaoImpl implements CourseDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public Course save(Course course) {
        String query = "" +
                "INSERT INTO tb_courses(name, subject, course_duration_in_month, lesson_duration, course_price) " +
                "VALUES(:name, :subject, :course_duration_in_month, :lesson_duration, :course_price)";

        KeyHolder holder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("name", course.getName())
                .addValue("subject",      course.getSubject())
                .addValue("courseDurationInMonth", course.getCourseDurationInMonth())
                .addValue("lessonDuration",      course.getLessonDuration())
                .addValue("coursePrice",course.getCoursePrice());

        namedParameterJdbcTemplate.update(query, source, holder, new String[]{"id"});

        course.setId(holder.getKey().longValue());
        return course;
    }
}
