package org.devs.crm.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phoneNumber;
    private List<Group> groups;

}
