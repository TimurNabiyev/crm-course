package org.devs.crm.dao.impl;

import org.devs.crm.dao.StudentDao;
import org.devs.crm.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    public DataSource dataSource;

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public Student save(Student student) {
        String query = "" +
                "INSERT INTO tb_students(first_name, last_name, patronymic, email, phone_number) " +
                "VALUES(:fname, :lname, :patronymic, :email, :phoneNumber)";

        KeyHolder holder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("fname", student.getFirstName())
                .addValue("lname", student.getLastName())
                .addValue("patronymic", student.getPatronymic())
                .addValue("email", student.getEmail())
                .addValue("phoneNumber", student.getPhoneNumber());

        namedParameterJdbcTemplate.update(query, source, holder, new String[]{"id"});

        student.setId(holder.getKey().longValue());
        return student;
    }
}
