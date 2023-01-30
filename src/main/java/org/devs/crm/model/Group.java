package org.devs.crm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Group {

    private Long id;
    private Course course;
    private List<Student> students;
    private List<Mentor> mentors;
    private LocalDate starDate;

}