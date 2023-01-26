package org.devs.crm;

import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.model.Mentor;
import org.devs.crm.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class CrmApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        StudentDao studentDao = context.getBean("studentDaoImpl", StudentDao.class);

        Student student = new Student();
        student.setFirstName(" John");
        student.setLastName(" Doe");
        student.setPatronymic(" Something");
        student.setEmail(" doe@gmail.com");
        student.setPhoneNumber(" +999 999 999 999");

        studentDao.save(student);

        System.out.println(student);

        ApplicationContext context1 = new AnnotationConfigApplicationContext(DaoConfig.class);
        MentorDao mentorDao = context1.getBean("mentorDaoImpl", MentorDao.class);

        Mentor mentor = new Mentor();
        mentor.setFirstName(" Elbek");
        mentor.setLastName(" Tursunbekov");
        mentor.setPatronymic(" Something too");
        mentor.setEmail(" elbek@gmail.com");
        mentor.setPhoneNumber(" +999 998 888 888");
        mentor.setSalary(BigDecimal.valueOf( 5402596545465.465));

        mentorDao.save(mentor);

        System.out.println(mentor);
    }
}
