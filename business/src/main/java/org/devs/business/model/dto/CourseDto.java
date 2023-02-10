package org.devs.business.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class CourseDto {

    private final Long id;
    private final String name;
    private final String subject;
    private final Integer courseDurationInMonth;
    private final LocalTime lessonDuration;
    private final BigDecimal coursePrice;

}


