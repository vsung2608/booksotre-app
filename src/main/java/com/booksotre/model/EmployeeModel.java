package com.booksotre.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeModel {

    private Integer employeeId;
    private String name;
    private String position;
    private String phone;
    private String email;
    private String address;
    private String password;
    private String gender;
    private String dob;
    private Date create_at;
}

