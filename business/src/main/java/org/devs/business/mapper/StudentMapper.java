package org.devs.business.mapper;

import org.devs.business.model.dto.StudentDto;
import org.devs.crm.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements BaseMapper<Student, StudentDto> {
    @Override
    public Student toEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPatronymic(dto.getPatronymic());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setGroups(null);
        return entity;
    }

    @Override
    public StudentDto toDto(Student entity) {
        return new StudentDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPatronymic(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                null);
    }
}
