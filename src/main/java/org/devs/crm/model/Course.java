package org.devs.crm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {

    private Long id;
    private String name;
    private String subject;
    private Integer courseDurationInMonth;
    private LocalTime lessonDuration;
    private BigDecimal coursePrice;

}
