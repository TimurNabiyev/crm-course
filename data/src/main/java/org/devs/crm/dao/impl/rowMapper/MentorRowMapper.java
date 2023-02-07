package org.devs.crm.dao.impl.rowMapper;

import org.devs.crm.model.Mentor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MentorRowMapper implements RowMapper<Mentor> {

    @Override
    public Mentor mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Mentor.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .patronymic(rs.getString("patronymic"))
                .email(rs.getString("email"))
                .phoneNumber(rs.getString("phone_number"))
                .salary(rs.getBigDecimal("salary"))
                .build();
    }
}
