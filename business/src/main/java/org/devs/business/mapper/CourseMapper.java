package org.devs.business.mapper;

import org.devs.business.model.dto.CourseDto;
import org.devs.crm.entity.Course;
import org.springframework.stereotype.Component;


@Component
public class CourseMapper implements BaseMapper<Course, CourseDto>{

    @Override
    public Course toEntity(CourseDto dto) {
        return new Course(dto.getId(),
                dto.getName(),
                dto.getSubject(),
                dto.getCourseDurationInMonth(),
                dto.getLessonDuration(),
                dto.getCoursePrice());
    }
    @Override
    public CourseDto toDto(Course entity) {
        return new CourseDto(entity.getId(),
                entity.getName(),
                entity.getSubject(),
                entity.getCourseDurationInMonth(),
                entity.getLessonDuration(),
                entity.getCoursePrice());
    }

}
