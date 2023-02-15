package org.devs.business.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMentorRequest {


    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private BigDecimal salary;
}
