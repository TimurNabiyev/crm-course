package org.devs.business.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateGroupRequest {

    private String groupName; // Animals
    private LocalDate startDate; // 15-02-2023
    private List<Long> studentsIds; // [2, 1 ,3]
    private List<Long> mentorsIds; // [5]
    private Long courseId; // 7

}