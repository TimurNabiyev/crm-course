package org.devs.crm.dao.impl;

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
public class MentorDaoImpl implements MentorDao {
    @Autowired
    public DataSource dataSource;

    @Override
    public Mentor findById(Long id) {
        return null;
    }

    @Override
    public Mentor save(Mentor mentor) {
        String query = "" +
                "INSERT INTO tb_mentors(first_name, last_name, patronymic, email, phone_number, salary) " +
                "VALUES(:fname, :lname, :patronymic, :email, :phoneNumber, :salary)";

        KeyHolder holder = new GeneratedKeyHolder();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

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
