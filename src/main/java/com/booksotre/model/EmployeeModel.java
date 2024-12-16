package com.booksotre.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.*;

@Data
@Builder
public class EmployeeModel {
    private Integer employeeId;
    private String email;
    private String password;
    private String employeeName;
    private String phone;
    private String address;
    private String gender;
    private LocalDate dob;
    private String role;
    private Double salary;
    private String avatar;
    private Timestamp createAt;
}
