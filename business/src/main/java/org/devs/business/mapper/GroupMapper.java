package org.devs.business.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.devs.business.model.dto.CourseDto;
import org.devs.business.model.dto.GroupDto;
import org.devs.business.model.dto.MentorDto;
import org.devs.business.model.dto.StudentDto;
import org.devs.crm.entity.Course;
import org.devs.crm.entity.Group;
import org.devs.crm.entity.Mentor;
import org.devs.crm.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper implements BaseMapper<Group, GroupDto> {

    @Override
    public Group toEntity(GroupDto dto) {
        Course course = new Course(dto.getCourse().getId(),
                dto.getCourse().getName(),
                dto.getCourse().getSubject(),
                dto.getCourse().getCourseDurationInMonth(),
                dto.getCourse().getLessonDuration(),
                dto.getCourse().getCoursePrice());

        List<Student> students = dto.getStudents().stream()
                .map(entitySt -> new Student(entitySt.getId(),
                        entitySt.getFirstName(),
                        entitySt.getLastName(),
                        entitySt.getPatronymic(),
                        entitySt.getEmail(),
                        entitySt.getPhoneNumber(),
                        null)).collect(Collectors.toList());

        List<Mentor> mentors = dto.getMentors().stream()
                .map(entityMen -> new Mentor(entityMen.getId(),
                        entityMen.getFirstName(),
                        entityMen.getLastName(),
                        entityMen.getPatronymic(),
                        entityMen.getEmail(),
                        entityMen.getPhoneNumber(),
                        entityMen.getSalary())).collect(Collectors.toList());

        return new Group(dto.getId(), dto.getGroupName(), course, students, mentors, dto.getStartDate());
    }

    @Override
    public GroupDto toDto(Group entity) {
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
