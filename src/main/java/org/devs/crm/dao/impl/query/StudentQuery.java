package org.devs.crm.dao.impl.query;

public class StudentQuery {

    public static final String SELECT_ONE = "SELECT * FROM tb_students WHERE id = :id;";
    public static final String SAVE_STUDENT = "" +
            "INSERT INTO tb_students(first_name, last_name, patronymic, email, phone_number) " +
            "VALUES(:fname, :lname, :patronymic, :email, :phoneNumber);";

    public static final String SELECT_ALL_BY_GROUP = "" +
            "SELECT s.* FROM tb_students s " +
            "JOIN group_has_student gs ON gs.student_id = s.id " +
            "WHERE gs.group_id = :id;";


}
