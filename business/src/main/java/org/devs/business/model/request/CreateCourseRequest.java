package org.devs.business.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
public class CreateCourseRequest {
    private String name;
    private String subject;
    private Integer courseDurationInMonth;
    private LocalTime lessonDuration;
    private BigDecimal coursePrice;
}
