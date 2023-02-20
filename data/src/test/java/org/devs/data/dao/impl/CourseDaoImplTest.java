package org.devs.data.dao.impl;

import org.assertj.core.api.Assertions;
import org.devs.data.dao.CourseDao;

import org.devs.data.dao.config.DaoConfig;
import org.devs.data.dao.exception.InvalidIdException;


import org.devs.data.dao.exception.NullParameterPassedException;
import org.devs.data.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;


import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@TestPropertySource(properties = "classpath:application.properties", locations = "classpath:.env")
public class CourseDaoImplTest {


    private CourseDao courseDao;


    @BeforeEach
    void setUp(@Autowired CourseDao courseDao,
               @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        executeSqlScript(namedParameterJdbcTemplate);
        this.courseDao = courseDao;

    }

    void executeSqlScript(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        DataSource dataSource = namedParameterJdbcTemplate.getJdbcTemplate().getDataSource();

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("script.sql"));

        populator.execute(dataSource);
    }


    @DisplayName("Should find group by id")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void shouldFindById(Long courseId) {


        Optional<Course> optionalCourse = courseDao.findById(courseId);

        Assertions.assertThat(optionalCourse).isNotNull();
        Assertions.assertThat(optionalCourse.isPresent()).isTrue();

        Course course = optionalCourse.get();

        Assertions.assertThat(course.getId()).isNotNull();
        Assertions.assertThat(course.getName()).isNotNull();
        Assertions.assertThat(course.getSubject()).isNotBlank();
        Assertions.assertThat(course.getCourseDurationInMonth()).isNotNull();
        Assertions.assertThat(course.getLessonDuration()).isNotNull();
        Assertions.assertThat(course.getCoursePrice()).isNotNull();



    }

    @DisplayName("Should throw InvalidIdException")
    @ParameterizedTest
    @ValueSource(longs = {-1, 0, -60})
    void shouldThrowExceptionOnFindById(Long id) {
        Assertions.assertThatExceptionOfType(InvalidIdException.class).isThrownBy(() -> courseDao.findById(id));
    }

    @DisplayName("Should not find group and return Optional.empty()")
    @ParameterizedTest
    @ValueSource(longs = {26903859L, 19075349001L})
    void shouldNotFindById(Long id) {
        Optional<Course> optionalCourse = courseDao.findById(id);

        Assertions.assertThat(optionalCourse).isNotNull();
        Assertions.assertThat(optionalCourse.isPresent()).isFalse();
    }


    @ParameterizedTest
    @MethodSource("coursesProvider")
    void shouldThrowExceptionOnSave(Course course) {
        Assertions.assertThatExceptionOfType(NullParameterPassedException.class).isThrownBy(() -> courseDao.save(course));
    }

}

