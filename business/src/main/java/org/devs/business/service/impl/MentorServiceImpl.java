package org.devs.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.devs.business.exception.MentorNotFoundException;
import org.devs.business.mapper.MentorMapper;
import org.devs.business.model.dto.MentorDto;
import org.devs.business.model.request.CreateMentorRequest;
import org.devs.business.service.MentorService;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.entity.Mentor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorDao mentorDao;
    private final MentorMapper mentorMapper;

    @Override
    @Transactional
    public MentorDto create(CreateMentorRequest mentorRequest) {

        if (mentorRequest == null || mentorRequest.getFirstName().isEmpty() || mentorRequest.getLastName().isEmpty()
                || mentorRequest.getEmail().isEmpty() || mentorRequest.getPhoneNumber().isEmpty()
                || mentorRequest.getSalary())) {
            throw  new IllegalArgumentException();
        }
        Mentor mentor = Mentor.builder()
                .firstName(mentorRequest.getFirstName())
                .lastName(mentorRequest.getLastName())
                .patronymic(mentorRequest.getPatronymic())
                .email(mentorRequest.getEmail())
                .phoneNumber(mentorRequest.getPhoneNumber())
                .salary(mentorRequest.getSalary())
                .build();

        mentorDao.save(mentor);

        return mentorMapper.toDto(mentor);
    }

    @Override
    public MentorDto getOne(Long id) {
        return mentorMapper.toDto(mentorDao.findById(id).orElseThrow(() -> new MentorNotFoundException("For id = " + id)));
    }

}
