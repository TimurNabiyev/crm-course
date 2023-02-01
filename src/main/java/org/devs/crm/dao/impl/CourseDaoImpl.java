package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.dao.impl.query.CourseQuery;
import org.devs.crm.dao.impl.rowMapper.CourseRowMapper;
import org.devs.crm.model.Course;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseDaoImpl implements CourseDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CourseRowMapper courseRowMapper;

    @Override
    public Optional<Course> findById(Long id) {

        return namedParameterJdbcTemplate.query(CourseQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), courseRowMapper).stream().findFirst();
    }

    @Override
    public Course save(Course course) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("name", course.getName())
                .addValue("subject", course.getSubject())
                .addValue("course_duration_in_month", course.getCourseDurationInMonth())
                .addValue("lesson_duration", course.getLessonDuration())
                .addValue("course_price", course.getCoursePrice());
        namedParameterJdbcTemplate.update(CourseQuery.SAVE_COURSE, source, keyHolder, new String[]{"id"});

        return course;
    }
}
