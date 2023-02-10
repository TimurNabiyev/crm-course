package org.devs.business.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateGroupRequest {

    private String groupName;
    private LocalDate startDate;
    private List<Long> studentsIds;
    private List<Long> mentorsIds;
    private Long courseId;

}
