package org.devs.business.service;

import org.devs.business.model.dto.CourseDto;

import org.devs.business.model.request.CreateCourseRequest;


public interface CourseService {
    CourseDto create(CreateCourseRequest course);
    CourseDto getOne(Long id);

}
