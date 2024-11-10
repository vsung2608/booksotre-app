package com.booksotre.model;

import java.sql.Timestamp;

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
    private String dob;
    private String role;
    private Double salary;
    private String avatar;
    private Timestamp createAt;
}
