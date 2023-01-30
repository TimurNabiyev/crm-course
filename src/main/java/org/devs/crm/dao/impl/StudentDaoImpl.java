package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.model.Student;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Student findById(Long id) {
        String query = "SELECT * FROM tb_students WHERE id = :id";

        return namedParameterJdbcTemplate.query(query, new MapSqlParameterSource("id", id), (rs, rowNum) -> {
            System.out.println(rowNum);
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setPatronymic(rs.getString("patronymic"));
            student.setEmail(rs.getString("email"));
            student.setPhoneNumber(rs.getString("phone_number"));
            return student;
        }).get(0);
    }

    @Override
    public Student save(Student student) {
        String query = "" +
                "INSERT INTO tb_students(first_name, last_name, patronymic, email, phone_number) " +
                "VALUES(:fname, :lname, :patronymic, :email, :phoneNumber)";

        KeyHolder holder = new GeneratedKeyHolder();

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
