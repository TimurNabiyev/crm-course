package org.devs.crm.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private BigDecimal salary;

}
