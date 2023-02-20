package org.devs.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.devs.business.exception.CourseNotFoundException;
import org.devs.business.mapper.CourseMapper;
import org.devs.business.model.dto.CourseDto;

import org.devs.business.model.request.CreateCourseRequest;

import org.devs.business.service.CourseService;
import org.devs.data.dao.CourseDao;
import org.devs.data.entity.Course;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private final CourseMapper courseMapper;

    @Override
    @Transactional
    public CourseDto create(CreateCourseRequest req) {

        Course course = Course.builder()
                .name(req.getName())
                .subject(req.getSubject())
                .courseDurationInMonth(req.getCourseDurationInMonth())
                .lessonDuration(req.getLessonDuration())
                .coursePrice(req.getCoursePrice())
                .build();

        courseDao.save(course);

        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto getOne(Long id) {
        return courseMapper.toDto(courseDao.findById(id).orElseThrow(() -> new CourseNotFoundException("For id=" + id)));
    }
}

