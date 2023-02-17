package org.devs.crm.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.exception.EntityNotFoundException;
import org.devs.crm.dao.exception.InvalidIdException;
import org.devs.crm.dao.exception.NullParameterPassedException;
import org.devs.crm.dao.impl.query.GroupQuery;
import org.devs.crm.dao.impl.rowMapper.GroupRowMapper;
import org.devs.crm.entity.Group;
import org.devs.crm.entity.Mentor;
import org.devs.crm.entity.Student;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final TransactionTemplate transactionTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GroupRowMapper groupRowMapper;
    private final StudentDao studentDao;
    private final MentorDao mentorDao;
    private final CourseDao courseDao;

    @Override
    public Optional<Group> findById(Long id) {
        checkId(id);
        return namedParameterJdbcTemplate.query(GroupQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), groupRowMapper).stream().peek(group -> {
            group.setCourse(courseDao.findByGroupId(id)
                    .orElseThrow(() -> new EntityNotFoundException("Course not found by id: " + id)));
            group.setMentors(mentorDao.findAllByGroupId(id));
            group.setStudents(studentDao.findAllByGroupId(id));
        }).findFirst();
    }

    @Override
    public Group save(Group group) {
        checkForNull(group.getGroupName());
        checkForNull(group.getCourse());
        checkForNull(group.getStudents());
        checkForNull(group.getStartDate());
        checkForNull(group.getMentors());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource().addValue("group_name", group.getGroupName())
                .addValue("start_date", group.getStartDate())
                .addValue("course_id", group.getCourse().getId());

        transactionTemplate.execute(status -> {
            namedParameterJdbcTemplate.update(GroupQuery.INSERT_ONE, source, keyHolder, new String[]{"id"});
            group.setId(keyHolder.getKeyAs(Long.class));

            saveToStudentJunction(group.getStudents(), group.getId());
            saveToMentorJunction(group.getMentors(), group.getId());
            return group;
        });

        return group;
    }

    private void saveToStudentJunction(List<Student> students, Long groupId) {
        MapSqlParameterSource[] sources = students.stream().map(student -> new MapSqlParameterSource()
                .addValue("group_id", groupId)
                .addValue("student_id", student.getId())).toArray(MapSqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(GroupQuery.INSERT_STUDENTS_JUNCTION, sources);
    }

    private void saveToMentorJunction(List<Mentor> mentors, Long groupId) {
        MapSqlParameterSource[] sources = mentors.stream().map(mentor -> new MapSqlParameterSource()
                .addValue("group_id", groupId)
                .addValue("mentor_id", mentor.getId())).toArray(MapSqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(GroupQuery.INSERT_MENTORS_JUNCTION, sources);
    }

    private void checkId(Long id) {
        if (id <= 0) {
            throw new InvalidIdException("Expected id greater than 0 but was send: " + id);
        }
    }

    private void checkForNull(Object object) {
        if (object == null) {
            throw new NullParameterPassedException("Argument is null");
        }
    }

    @Override
    public Optional<Group> findByGroupName(String name) {
        checkForNull(name);
        return namedParameterJdbcTemplate.query(GroupQuery.SELECT_ONE_BY_GROUP_NAME,
                new MapSqlParameterSource("groupName", name), groupRowMapper).stream().peek(group -> {
            group.setCourse(courseDao.findByGroupId(group.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Course not found by id: " + group.getId())));
            group.setMentors(mentorDao.findAllByGroupId(group.getId()));
            group.setStudents(studentDao.findAllByGroupId(group.getId()));
        }).findFirst();
    }
}
