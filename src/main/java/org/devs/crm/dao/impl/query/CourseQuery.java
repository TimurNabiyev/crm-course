package org.devs.crm.dao.impl.query;

public class CourseQuery {

    public static final String SELECT_ONE = "SELECT * FROM tb_courses WHERE id = :id";
    public static final String SAVE_COURSE = "" +
            "INSERT INTO tb_courses(name, subject, course_duration_in_month, lesson_duration, course_price) " +
            "VALUES(:name, :subject, :course_duration_in_month, :lesson_duration, :course_price)";
}
