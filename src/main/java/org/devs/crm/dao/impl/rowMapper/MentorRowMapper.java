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
                .id(rs.getLong("m.id"))
                .firstName(rs.getString("m.firstName"))
                .lastName(rs.getString("m.lastName"))
                .patronymic(rs.getString("m.patronymic"))
                .email(rs.getString("m.email"))
                .phoneNumber(rs.getString("m.phoneNumber"))
                .salary(rs.getBigDecimal("m.salary"))
                .build();
    }
}
