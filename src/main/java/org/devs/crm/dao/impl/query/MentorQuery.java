package org.devs.crm.dao.impl.query;

public class MentorQuery {

    public static final String SELECT_ONE = "" + "SELECT * FROM tb_mentors m " +
            "JOIN tb_courses c ON m.course_id = c.id " +
            "WHERE id = :id";
}
