package org.devs.crm.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    private Long id;
    private String groupName;
    private Course course;
    private List<Student> students;
    private List<Mentor> mentors;
    private LocalDate startDate;

}