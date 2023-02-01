package org.devs.crm.dao.impl.query;

public class MentorQuery {

    public static final String SELECT_ONE = "SELECT * FROM tb_mentors WHERE id = :id";
    public static final String SAVE_MENTOR = "" +
            "INSERT INTO tb_mentors(first_name, last_name, patronymic, email, phone_number, salary " +
            "VALUES(:fname, :lname, :patronymic, :phone_number, :salary";

    public static final String SELECT_ALL_BY_GROUP = "" +
            "SELECT m.* FROM tb_mentors m " +
            "JOIN group_has_mentor gm ON gm.mentor_id = m.id " +
            "WHERE gm.group_id = :id;";
}
