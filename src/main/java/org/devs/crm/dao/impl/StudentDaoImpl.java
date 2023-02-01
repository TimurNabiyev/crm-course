package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.impl.query.StudentQuery;
import org.devs.crm.dao.impl.rowMapper.StudentRowMapper;
import org.devs.crm.model.Student;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final StudentRowMapper studentRowMapper;

    @Override
    public Optional<Student> findById(Long id) {
        return namedParameterJdbcTemplate.query(StudentQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), studentRowMapper).stream().findFirst();
        }


    @Override
    public Student save(Student student) {
        KeyHolder holder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("fname", student.getFirstName())
                .addValue("lname", student.getLastName())
                .addValue("patronymic", student.getPatronymic())
                .addValue("email", student.getEmail())
                .addValue("phoneNumber", student.getPhoneNumber());

        namedParameterJdbcTemplate.update(StudentQuery.SAVE_STUDENT, source, holder, new String[]{"id"});
        student.setId(holder.getKey().longValue());

        return student;
    }
}
