package org.devs.crm.dao.impl;

import org.assertj.core.api.Assertions;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class) // Test run with Spring
@ContextConfiguration(classes = DaoConfig.class) // @Configuration
@TestPropertySource(properties = "classpath:application.properties", locations = "classpath:.env") // Database config
class GroupDaoImplTest {

    private GroupDao groupDao;

    @BeforeEach
    void setUp(@Autowired
               GroupDao groupDao,
               @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        executeSqlScript(namedParameterJdbcTemplate);

        this.groupDao = groupDao;
    }

    void executeSqlScript(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        DataSource dataSource = namedParameterJdbcTemplate.getJdbcTemplate().getDataSource();

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("script.sql"));

        populator.execute(dataSource);
    }

    @ParameterizedTest
    @MethodSource("")
    void shouldFindById(Group group) {
        Group cloned = cloneGroup(group);

        groupDao.save(group);

        cloned.setId(group.getId());

        Assertions.assertThat(group).isNotNull();
        Assertions.assertThat(group.getId()).isNotNull();
        Assertions.assertThat(group).usingRecursiveComparison().isEqualTo(cloned);
    }

    @ParameterizedTest
    @MethodSource("groupsProviderGenerateException")
    void shouldThrowExceptionOnFindById(Group group) {
        Assertions.assertThatNullPointerException().isThrownBy(() -> groupDao.save(group));
    }
    @ParameterizedTest
    @MethodSource("")
    void shouldNotFindById(Group group) {
        Assertions.assertThat(group).isNull();
    }

    void shouldSaveNew() {

    }

    void shouldThrowExceptionOnSave() {

    }

    void shouldNotSave() {

    }

    private static Stream<Arguments> groupsProviderGenerateException() {
        return IntStream.range(1, 51).mapToObj(index -> Arguments.of(Group.builder()
                        .groupName("Group #" + index)
                        .startDate(LocalDate.now().plusDays(index))
                .build()));
    }

    private Group cloneGroup(Group group) {
        return Group.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .course(group.getCourse())
                .mentors(group.getMentors())
                .students(group.getStudents())
                .startDate(group.getStartDate())
                .build();
    }

}