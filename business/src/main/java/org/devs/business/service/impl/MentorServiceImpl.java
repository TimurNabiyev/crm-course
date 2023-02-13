package org.devs.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.devs.business.model.dto.MentorDto;
import org.devs.business.model.request.CreateMentorRequest;
import org.devs.business.service.MentorService;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.entity.Mentor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorDao mentorDao;

    @Override
    @Transactional
    public MentorDto create(CreateMentorRequest mentorRequest) {
        Mentor mentor = Mentor.builder()
                .id(mentorRequest.getId())
                .firstName(mentorRequest.getFirstName())
                .lastName(mentorRequest.getLastName())
                .patronymic(mentorRequest.getPatronymic())
                .email(mentorRequest.getEmail())
                .phoneNumber(mentorRequest.getPhoneNumber())
                .salary(mentorRequest.getSalary())
                .build();

        mentorDao.save(mentor);

        return null;
    }

}
