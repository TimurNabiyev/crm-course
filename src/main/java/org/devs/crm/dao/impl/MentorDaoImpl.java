package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.model.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class MentorDaoImpl implements MentorDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Mentor findById(Long id) {
        String query = "SELECT * FROM tb_mentors m " +
                "JOIN tb_cources c ON m.course_id = c.id " +
                "WHERE id = :id";

        return namedParameterJdbcTemplate.query(query, new MapSqlParameterSource("id", id), (rs, rowNum) -> {
            System.out.println(rowNum);
            Mentor mentor = new Mentor();
            mentor.setId(rs.getLong("m.id"));
            mentor.setFirstName(rs.getString("m.firstName"));
            mentor.setLastName(rs.getString("m.lastName"));
            mentor.setPatronymic(rs.getString("m.patronymic"));
            mentor.setEmail(rs.getString("m.email"));
            mentor.setPhoneNumber(rs.getString("m.phoneNumber"));
            mentor.setSalary(rs.getBigDecimal("m.salary"));
            return mentor;
        }).get(0);
    }

    @Override
    public Mentor save(Mentor mentor) {
        String query = "" +
                "INSERT INTO tb_mentors(first_name, last_name, patronymic, email, phone_number, salary) " +
                "VALUES(:fname, :lname, :patronymic, :email, :phoneNumber, :salary)";

        KeyHolder holder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("fname", mentor.getFirstName())
                .addValue("lname", mentor.getLastName())
                .addValue("patronymic", mentor.getPatronymic())
                .addValue("email", mentor.getEmail())
                .addValue("phoneNumber", mentor.getPhoneNumber())
                .addValue("salary", mentor.getSalary());

        namedParameterJdbcTemplate.update(query, source, holder, new String[]{"id"});

        mentor.setId(holder.getKey().longValue());
        return mentor;
    }
}
