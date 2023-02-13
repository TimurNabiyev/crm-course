package org.devs.business.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class GroupDto {

    private final Long id;
    private final String groupName;
    private final CourseDto courseDto;
    private final List<StudentDto> studentDtos;
    private final List<MentorDto> mentorDtos;
    private final LocalDate startDate;

}