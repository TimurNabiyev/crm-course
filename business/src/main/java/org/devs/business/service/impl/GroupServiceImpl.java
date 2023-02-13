package org.devs.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.devs.business.model.dto.CourseDto;
import org.devs.business.model.dto.GroupDto;
import org.devs.business.model.dto.MentorDto;
import org.devs.business.model.dto.StudentDto;
import org.devs.business.model.request.CreateGroupRequest;
import org.devs.business.service.GroupService;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.entity.Course;
import org.devs.crm.entity.Group;
import org.devs.crm.entity.Mentor;
import org.devs.crm.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private final StudentDao studentDao;
    private final MentorDao mentorDao;
    private final CourseDao courseDao;

    @Override
    @Transactional
    public GroupDto create(CreateGroupRequest req) {
        List<Student> students = req.getStudentsIds().stream()
                .map(id -> studentDao.findById(id).orElseThrow(RuntimeException::new)).collect(Collectors.toList());

        List<Mentor> mentors = req.getMentorsIds().stream()
                .map(id -> mentorDao.findById(id).orElseThrow(RuntimeException::new)).collect(Collectors.toList());

        Course course = courseDao.findById(req.getCourseId()).orElseThrow(RuntimeException::new);

        Group group = Group.builder()
                .groupName(req.getGroupName())
                .students(students)
                .course(course)
                .mentors(mentors)
                .startDate(req.getStartDate())
                .build();

        groupDao.save(group);

        return mapToDto(group);
    }

    private GroupDto mapToDto(Group entity) {
        CourseDto courseDto = new CourseDto(entity.getCourse().getId(),
                entity.getCourse().getName(),
                entity.getCourse().getSubject(),
                entity.getCourse().getCourseDurationInMonth(),
                entity.getCourse().getLessonDuration(),
                entity.getCourse().getCoursePrice());

        List<StudentDto> studentDtos = entity.getStudents().stream()
                .map(entitySt -> new StudentDto(entitySt.getId(),
                        entitySt.getFirstName(),
                        entitySt.getLastName(),
                        entitySt.getPatronymic(),
                        entitySt.getEmail(),
                        entitySt.getPhoneNumber(),
                        null)).collect(Collectors.toList());

        List<MentorDto> mentorDtos = entity.getMentors().stream()
                .map(entityMen -> new MentorDto(entityMen.getId(),
                        entityMen.getFirstName(),
                        entityMen.getLastName(),
                        entityMen.getPatronymic(),
                        entityMen.getEmail(),
                        entityMen.getPhoneNumber(),
                        entityMen.getSalary())).collect(Collectors.toList());

        return new GroupDto(entity.getId(), entity.getGroupName(), courseDto, studentDtos, mentorDtos, entity.getStartDate());
    }
}
