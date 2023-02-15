package org.devs.business.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GroupDto {

    private final Long id;
    private final String groupName;
    private final CourseDto course;
    private final List<StudentDto> students;
    private final List<MentorDto> mentors;
    private final LocalDate startDate;

}