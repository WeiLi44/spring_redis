package com.example.spring_redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

// no @Entity concept here
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 6126856664979949099L;

    private String id;
    private String name;
    private String dept;
    private Integer years;
    private Date startDate;

}
