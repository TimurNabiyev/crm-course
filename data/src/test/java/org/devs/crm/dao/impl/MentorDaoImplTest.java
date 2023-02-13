package org.devs.crm.dao.impl;


import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import org.assertj.core.api.Assertions;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.dao.exception.InvalidIdException;
import org.devs.crm.model.Mentor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@TestPropertySource(properties = "classpath:application.properties", locations = "classpath:.env")
public class MentorDaoImplTest {

    private MentorDao mentorDao;

    @BeforeEach
    void setUp(@Autowired MentorDao mentorDao,
               @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        executeSqlScript(namedParameterJdbcTemplate);

        this.mentorDao = mentorDao;
    }

    void executeSqlScript(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        DataSource dataSource = namedParameterJdbcTemplate.getJdbcTemplate().getDataSource();

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("script.sql"));

        populator.execute(dataSource);
    }

    @DisplayName("Should find mentor by id")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void shouldFindById(Long mentorId) {
        Optional<Mentor> optionalMentor = mentorDao.findById(mentorId);
        List<Mentor> mentorList = mentorDao.findAllByGroupId(mentorId);

        Assertions.assertThat(optionalMentor).isNotNull();
        Assertions.assertThat(optionalMentor.isPresent()).isTrue();

        Mentor mentor = optionalMentor.get();

        Assertions.assertThat(mentor.getId()).isNotNull();
        Assertions.assertThat(mentor.getFirstName()).isNotNull();
        Assertions.assertThat(mentor.getLastName()).isNotNull();
        Assertions.assertThat(mentor.getPatronymic());
        Assertions.assertThat(mentor.getEmail()).isNotNull();
        Assertions.assertThat(mentor.getPhoneNumber()).isNotNull();
        Assertions.assertThat(mentor.getSalary()).isNotNull();

        Assertions.assertThat(mentorList).isNotNull();
    }

    @DisplayName("Should throw InvalidIdException")
    @ParameterizedTest
    @ValueSource(longs = {-1, 0, -80})
    void shouldThrowExceptionOnFIndById(Long id) {
        Assertions.assertThatExceptionOfType(InvalidIdException.class).isThrownBy(() -> mentorDao.findById(id));
    }

    @DisplayName("Should not find mentor and return Optional.empty()")
    @ParameterizedTest
    @ValueSource(longs = {1546546, 4561325646L})
    void shouldNotFindById(Long id) {
        Optional<Mentor> optionalMentor = mentorDao.findById(id);

        Assertions.assertThat(optionalMentor).isNotNull();
        Assertions.assertThat(optionalMentor.isPresent()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("mentorsProviderGenerateException")
    void shouldThrowExceptionOnSave(Mentor mentor) {
        Assertions.assertThatNullPointerException().isThrownBy(() -> mentorDao.save(mentor));
    }

    void shouldSaveNew(){

    }

    void shouldNotSave(){

    }

    private static Stream<Arguments> mentorsProviderGenerateException() {
        return IntStream.range(1,100).mapToObj(index -> Arguments.of(Mentor.builder()
                .firstName("Name " + index)
                .lastName("Last")
                .patronymic("Patronymic")
                .email("KSLADASDKAS2342342@akl;ms.ewre")
                .phoneNumber("545465465466")
                .salary(BigDecimal.valueOf(654554))
                .build()));
    }

    private Mentor cloneMentor(Mentor mentor) {
        return Mentor.builder()
                .id(mentor.getId())
                .firstName(mentor.getFirstName())
                .lastName(mentor.getLastName())
                .patronymic(mentor.getPatronymic())
                .email(mentor.getEmail())
                .phoneNumber(mentor.getPhoneNumber())
                .salary(mentor.getSalary())
                .build();
    }

}
