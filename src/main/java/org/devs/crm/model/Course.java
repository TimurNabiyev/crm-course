package org.devs.crm.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long id;
    private String name;
    private String subject;
    private Integer courseDurationInMonth;
    private LocalTime lessonDuration;
    private BigDecimal coursePrice;

}


