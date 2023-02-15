package org.devs.business.model.request;

import lombok.Data;
import org.devs.crm.entity.Group;

import java.util.List;

@Data
public class CreateStudentRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private List<Group> groups;
}
