package org.devs.business.service.impl;

import lombok.RequiredArgsConstructor;
import org.devs.business.mapper.StudentMapper;
import org.devs.business.model.dto.StudentDto;
import org.devs.business.model.request.CreateStudentRequest;
import org.devs.business.service.StudentService;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
   private final StudentDao studentDao;
   private final StudentMapper studentMapper;

    @Override
    @Transactional
    public StudentDto create(CreateStudentRequest studentRequest) {
        Student student = Student.builder()
                .id(studentRequest.getId())
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .patronymic(studentRequest.getPatronymic())
                .email(studentRequest.getEmail())
                .phoneNumber(studentRequest.getPhoneNumber())
                .groups(studentRequest.getGroups())
                .build();

        studentDao.save(student);

        return studentMapper.toDto(student);
    }
}
