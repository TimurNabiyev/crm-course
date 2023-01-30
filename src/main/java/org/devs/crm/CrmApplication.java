package org.devs.crm;

import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CrmApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        StudentDao studentDao = context.getBean("studentDaoImpl", StudentDao.class);

        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setPatronymic("Something");
        student.setEmail("doe@gmail.com");
        student.setPhoneNumber("+999 999 999 999");

        studentDao.save(student);

        System.out.println(student);
    }
}
