package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.impl.query.GroupQuery;
import org.devs.crm.dao.impl.rowMapper.GroupRowMapper;
import org.devs.crm.model.Group;
import org.devs.crm.model.Mentor;
import org.devs.crm.model.Student;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GroupRowMapper groupRowMapper;
    private final StudentDao studentDao;
    private final MentorDao mentorDao;

    @Override
    public Optional<Group> findById(Long id) {
         return namedParameterJdbcTemplate.query(GroupQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), groupRowMapper).stream().peek(group -> {
                    group.setMentors(mentorDao.findAllByGroupId(id));
                    group.setStudents(studentDao.findAllByGroupId(id));
         }).findFirst();
    }

    @Override
    public Group save(Group group) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource().addValue("group_name", group.getGroupName())
                .addValue("start_date", group.getStartDate())
                .addValue("course_id", group.getCourse().getId());

        namedParameterJdbcTemplate.update(GroupQuery.INSERT_ONE, source, keyHolder, new String[]{"id"});
        group.setId(keyHolder.getKeyAs(Long.class));

        saveToStudentJunction(group.getStudents(), group.getId());
        saveToMentorJunction(group.getMentors(), group.getId());

        return group;
    }

    private void saveToStudentJunction(List<Student> students, Long groupId) { // {1, 5, 7, 15},    2
        students.forEach(student -> namedParameterJdbcTemplate.update(GroupQuery.INSERT_STUDENTS_JOINED,
                new MapSqlParameterSource()
                .addValue("group_id", groupId)
                .addValue("student_id", student.getId())));
    }

    private void saveToMentorJunction(List<Mentor> mentors, Long groupId) {
        mentors.forEach(mentor -> namedParameterJdbcTemplate.update(GroupQuery.INSERT_MENTORS_JOINED,
                new MapSqlParameterSource()
                        .addValue("group_id", groupId)
                        .addValue("mentor_id", mentor.getId())));
    }
}
