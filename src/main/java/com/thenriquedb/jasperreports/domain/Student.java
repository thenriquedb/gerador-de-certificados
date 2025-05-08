package com.thenriquedb.jasperreports.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    String name;
    String course;
    Integer courseDuration;
    Date startDate;
    Date endDate;
}
