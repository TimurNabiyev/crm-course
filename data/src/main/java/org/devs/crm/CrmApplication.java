package org.devs.crm;

import org.devs.crm.dao.CourseDao;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.dao.config.DaoConfig;
import org.devs.crm.dao.exception.EntityNotFoundException;
import org.devs.crm.model.Course;
import org.devs.crm.model.Group;
import org.devs.crm.model.Mentor;
import org.devs.crm.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CrmApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);

        StudentDao studentDao = context.getBean(StudentDao.class);
        MentorDao mentorDao = context.getBean(MentorDao.class);
        CourseDao courseDao = context.getBean(CourseDao.class);
        GroupDao groupDao = context.getBean(GroupDao.class);


        List<Student> studentList = LongStream.range(1, 11)
                .mapToObj(id -> studentDao.findById(id).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toList());

        List<Mentor> mentors = LongStream.range(1, 11)
                .mapToObj(id -> mentorDao.findById(id).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toList());

        Course course = courseDao.findById(4L).orElseThrow(EntityNotFoundException::new);

        Group group = Group.builder()
                .groupName("Zebra")
                .course(course)
                .mentors(mentors)
                .students(studentList)
                .startDate(LocalDate.of(2023, 02, 03))
                .build();

        groupDao.save(group);

        System.out.println(group);
    }
}
