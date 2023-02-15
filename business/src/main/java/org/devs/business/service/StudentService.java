package org.devs.business.service;

import org.devs.business.model.dto.StudentDto;
import org.devs.business.model.request.CreateStudentRequest;

public interface StudentService {
    StudentDto create(CreateStudentRequest studentRequest);
}
