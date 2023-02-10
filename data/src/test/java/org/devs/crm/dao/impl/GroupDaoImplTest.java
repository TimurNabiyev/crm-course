package org.devs.crm.dao.impl;

import org.assertj.core.api.Assertions;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.dao.exception.InvalidIdException;
import org.devs.crm.dao.exception.NullParameterPassedException;
import org.devs.crm.model.Course;
import org.devs.crm.model.Group;
import org.devs.crm.model.Mentor;
import org.devs.crm.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class) // Test run with Spring
@ContextConfiguration(classes = DaoConfig.class) // @Configuration
@TestPropertySource(properties = "classpath:application.properties", locations = "classpath:.env") // Database config
class GroupDaoImplTest {

    private GroupDao groupDao;
    private StudentDao studentDao;
    private CourseDao courseDao;
    private MentorDao mentorDao;

    @BeforeEach
    void setUp(@Autowired GroupDao groupDao,
               @Autowired StudentDao studentDao,
               @Autowired CourseDao courseDao,
               @Autowired MentorDao mentorDao,
               @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        executeSqlScript(namedParameterJdbcTemplate);

        this.groupDao = groupDao;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.mentorDao = mentorDao;
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
    void shouldFindById(Long groupId) {

        List<Student> students = studentDao.findAllByGroupId(groupId);
        List<Mentor> mentors = mentorDao.findAllByGroupId(groupId);
        Optional<Course> optionalCourse = courseDao.findByGroupId(groupId);

        Optional<Group> optionalGroup = groupDao.findById(groupId);

        Assertions.assertThat(optionalGroup).isNotNull();
        Assertions.assertThat(optionalGroup.isPresent()).isTrue();

        Group group = optionalGroup.get();

        Assertions.assertThat(group.getId()).isNotNull();
        Assertions.assertThat(group.getGroupName()).isNotNull();
        Assertions.assertThat(group.getGroupName()).isNotBlank();
        Assertions.assertThat(group.getCourse()).isNotNull();
        Assertions.assertThat(group.getMentors()).isNotNull();
        Assertions.assertThat(group.getStartDate()).isNotNull();
        Assertions.assertThat(group.getStudents()).isNotNull();

        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(mentors).isNotNull();
        Assertions.assertThat(optionalCourse).isNotNull();
        Assertions.assertThat(optionalCourse.isPresent()).isTrue();

        Assertions.assertThat(students).usingRecursiveComparison().isEqualTo(group.getStudents());
        Assertions.assertThat(mentors).usingRecursiveComparison().isEqualTo(group.getMentors());
        Assertions.assertThat(optionalCourse.get()).usingRecursiveComparison().isEqualTo(group.getCourse());
    }

    @DisplayName("Should throw InvalidIdException")
    @ParameterizedTest
    @ValueSource(longs = {-1, 0, -80})
    void shouldThrowExceptionOnFindById(Long id) {
        Assertions.assertThatExceptionOfType(InvalidIdException.class).isThrownBy(() -> groupDao.findById(id));
    }

    @DisplayName("Should not find group and return Optional.empty()")
    @ParameterizedTest
    @ValueSource(longs = {14578987L, 12255447958L})
    void shouldNotFindById(Long id) {
        Optional<Group> optionalGroup = groupDao.findById(id);

        Assertions.assertThat(optionalGroup).isNotNull();
        Assertions.assertThat(optionalGroup.isPresent()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("groupsProvider")
    void shouldThrowExceptionOnSave(Group group) {
        Assertions.assertThatExceptionOfType(NullParameterPassedException.class).isThrownBy(() -> groupDao.save(group));
    }


    @ParameterizedTest
    @MethodSource("groupsProvider")
    void shouldSaveNew(Group group) {
        List<Student> students = studentDao.findAllByGroupId(3L);
        List<Mentor> mentors = mentorDao.findAllByGroupId(2L);
        Optional<Course> optionalCourse = courseDao.findByGroupId(1L);

        group.setStudents(students);
        group.setCourse(optionalCourse.get());
        group.setMentors(mentors);

        groupDao.save(group);

        Optional<Group> saved = groupDao.findById(group.getId());

        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.isPresent()).isTrue();

        Assertions.assertThat(group).usingRecursiveComparison().isEqualTo(saved.get());
    }

    @ParameterizedTest
    @MethodSource("groupsProviderGenerateDataAccessException")
    void shouldNotSaveAndThrowException(Group group) {
        Assertions.assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> groupDao.save(group));

        Optional<Group> optionalGroupFromDB = groupDao.findByGroupName(group.getGroupName());

        Assertions.assertThat(optionalGroupFromDB.isPresent()).isFalse();
    }

    private static Stream<Arguments> groupsProvider() {
        return IntStream.range(1, 51).mapToObj(index -> Arguments.of(Group.builder()
                .groupName("Group #" + index)
                .startDate(LocalDate.now().plusDays(index))
                .build()));
    }


    private static Stream<Arguments> groupsProviderGenerateDataAccessException() {
        List<Arguments> arguments = new ArrayList<>();

        List<Mentor> mentors = LongStream.range(1, 3)
                .mapToObj(index -> Mentor.builder()
                        .id(index)
                        .email("mentorsemail" + index + "@gmail.com")
                        .salary(BigDecimal.valueOf(index * 10000))
                        .lastName("mentor lastname #" + index)
                        .firstName("mentor firstname #" + index)
                        .patronymic("mentor patronymic #" + index)
                        .phoneNumber("+9989999999" + index)
                        .build()).collect(Collectors.toList());

        List<Student> students = LongStream.range(1, 5)
                .mapToObj(index -> Student.builder()
                        .id(index)
                        .lastName("student lastname #" + index)
                        .firstName("student firstname #" + index)
                        .patronymic("student patronymic #" + index)
                        .phoneNumber("+998999999" + index + "9")
                        .email("studentemail" + index + "@gmail.com")
                        .build()).collect(Collectors.toList());

        Course course = Course.builder()
                .id(1L)
                .courseDurationInMonth(7)
                .coursePrice(BigDecimal.valueOf(10000))
                .lessonDuration(LocalTime.of(2, 0, 0))
                .subject("Java")
                .name("Java Backend")
                .build();

        arguments.add(Arguments.of(Group.builder().groupName("Group #1").startDate(LocalDate.now().plusDays(5))
                .course(course).students(students).build()));

        arguments.add(Arguments.of(Group.builder().groupName("Group #2").startDate(LocalDate.now().plusDays(5))
                .students(students).mentors(mentors).build()));

        arguments.add(Arguments.of(Group.builder().groupName("Group #3").startDate(LocalDate.now().plusDays(5))
                .course(course).mentors(mentors).build()));

        arguments.add(Arguments.of(Group.builder().groupName("Group #4").course(course).students(students)
                .mentors(mentors).build()));

        return arguments.stream();
    }
}