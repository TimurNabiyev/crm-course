package org.devs.crm;

import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.model.Mentor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class CrmApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        MentorDao mentorDao = context.getBean("mentorDaoImpl", MentorDao.class);

        Mentor mentor = new Mentor();
        mentor.setFirstName("John");
        mentor.setLastName("Doe");
        mentor.setPatronymic("Something");
        mentor.setEmail("doe@gmail.com");
        mentor.setPhoneNumber("+999 999 999 999");
        mentor.setSalary(BigDecimal.valueOf(54645554.46546546546556));

        mentorDao.save(mentor);

        System.out.println(mentor);
    }
}
