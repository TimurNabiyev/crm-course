package org.devs.crm.dao.impl.rowMapper;

import org.devs.crm.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Student.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .patronymic(rs.getString("patronymic"))
                .email(rs.getString("email"))
                .phoneNumber(rs.getString("phone_number"))
                .build();
    }
}
