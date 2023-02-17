package org.devs.business.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String email;
    private final String phoneNumber;
    private final List<GroupDto> groupDtos;

}
