package org.devs.crm.dao.impl;

import lombok.RequiredArgsConstructor;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.exception.InvalidIdException;
import org.devs.crm.dao.exception.NullParameterPassedException;
import org.devs.crm.dao.impl.query.GroupQuery;
import org.devs.crm.dao.impl.query.MentorQuery;
import org.devs.crm.dao.impl.rowMapper.GroupRowMapper;
import org.devs.crm.dao.impl.rowMapper.MentorRowMapper;
import org.devs.crm.entity.Group;
import org.devs.crm.entity.Mentor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MentorDaoImpl implements MentorDao {

    private final TransactionTemplate transactionTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final MentorRowMapper mentorRowMapper;

    @Override
    public Optional<Mentor> findById(Long id) {
        checkId(id);
        return namedParameterJdbcTemplate.query(MentorQuery.SELECT_ONE,
                new MapSqlParameterSource("id", id), mentorRowMapper).stream().findFirst();
    }

    @Override
    public Mentor save(Mentor mentor) {
        checkForNull(mentor.getFirstName());
        checkForNull(mentor.getLastName());
        checkForNull(mentor.getEmail());
        checkForNull(mentor.getPhoneNumber());
        checkForNull(mentor.getSalary());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("first_name", mentor.getFirstName())
                .addValue("last_name", mentor.getLastName())
                .addValue("patronymic", mentor.getPatronymic())
                .addValue("email", mentor.getEmail())
                .addValue("phone_number", mentor.getPhoneNumber())
                .addValue("salary", mentor.getSalary());
        transactionTemplate.execute(status -> {
            namedParameterJdbcTemplate.update(MentorQuery.INSERT_ONE, source, keyHolder, new String[]{"id"});
            mentor.setId(keyHolder.getKeyAs(Long.class));

            return mentor;
        });
        return mentor;
    }

    private void checkId(Long id) {
        if (id <= 0) {
            throw new InvalidIdException("Expected id greater than 0 but was send: " + id);
        }
    }

    private void checkForNull(Object object) {
        if (object == null) {
            throw new NullParameterPassedException("Argument is null");
        }
    }

    @Override
    public List<Mentor> findAllByGroupId(Long groupId) {
        return null;
    }

    @Override
    public Optional<Mentor> findByMentorName(String first_name) {
        checkForNull(first_name);
        return namedParameterJdbcTemplate.query(MentorQuery.SELECT_ONE_BY_MENTOR_NAME,
                new MapSqlParameterSource("first_name", first_name),mentorRowMapper)
                .stream().findFirst();
    }
}
