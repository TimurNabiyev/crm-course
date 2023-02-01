package org.devs.crm.dao.impl.query;

public class StudentQuery {
    public static final String SELECT_ONE = "SELECT * FROM tb_students WHERE id = :id";
    public static final String SAVE_STUDENT = "" +
            "INSERT INTO tb_students(first_name, last_name, patronymic, email, phone_number) " +
            "VALUES(:fname, :lname, :patronybic, :email, :phoneNumber)";
}
