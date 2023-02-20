package org.devs.data.dao.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.devs.data.dao.MentorDao;
import org.devs.data.dao.impl.query.MentorQuery;
import org.devs.data.dao.impl.rowmapper.MentorRowMapper;
import org.devs.data.entity.Mentor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MentorDaoImpl implements MentorDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final MentorRowMapper mentorRowMapper;

    @Override
    public Optional<Mentor> findById(Long id) {
        return namedParameterJdbcTemplate.query(MentorQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), mentorRowMapper).stream().findFirst();
    }

    @Override
    public Mentor save(Mentor mentor) {

        KeyHolder keyHolder = new GeneratedKeyHolder();


        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("fname", mentor.getFirstName())
                .addValue("lname", mentor.getLastName())
                .addValue("paronymic", mentor.getEmail())
                .addValue("email", mentor.getEmail())
                .addValue("phone_number", mentor.getPhoneNumber())
                .addValue("salary", mentor.getSalary());
        namedParameterJdbcTemplate.update(MentorQuery.SAVE_MENTOR, source, keyHolder, new String[]{"id"});

        return mentor;
    }

    @Override
    public List<Mentor> findAllByGroupId(Long groupId) {
        return namedParameterJdbcTemplate.query(MentorQuery.SELECT_ALL_BY_GROUP,
                new MapSqlParameterSource("id", groupId), mentorRowMapper);
    }
}
