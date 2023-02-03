package org.devs.crm.dao.impl.query;

public class GroupQuery {

    public static final String SELECT_ONE = "" +
            "SELECT *, g.id AS group_id, c.id AS course_id FROM tb_groups g " +
            "JOIN tb_courses c ON g.course_id = c.id " +
            "WHERE g.id = :id";

    public static final String INSERT_MENTORS_JOINED = "INSERT INTO group_has_mentor VALUES (:group_id, :mentor_id);";

    public static final String INSERT_STUDENTS_JOINED = "INSERT INTO group_has_student VALUES (:group_id, :student_id);";

    public static final String INSERT_ONE =
            "INSERT INTO tb_groups(group_name, start_date, course_id) VALUES (:group_name, :start_date, :course_id);";
}
