package org.devs.crm.dao.impl.query;

public class GroupQuery {

    public static final String SELECT_ONE = "" +
            "SELECT *, g.id AS group_id, c.id AS course_id FROM tb_groups g " +
            "JOIN tb_courses c ON g.course_id = c.id " +
            "WHERE g.id = :id";

}
