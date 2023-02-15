package org.devs.business.mapper;

import org.devs.business.model.dto.MentorDto;
import org.devs.crm.entity.Mentor;

public class MentorMapper implements BaseMapper<Mentor, MentorDto> {
    @Override
    public Mentor toEntity(MentorDto dto) {
        return new Mentor(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPatronymic(), dto.getEmail(),
                dto.getPhoneNumber(), dto.getSalary());
    }

    @Override
    public MentorDto toDto(Mentor entity) {
        return new MentorDto(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getPatronymic(),
                entity.getEmail(), entity.getPhoneNumber(), entity.getSalary());
    }
}
