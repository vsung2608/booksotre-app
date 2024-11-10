package com.booksotre.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.*;

@Data
@Builder
public class CustomerModel {
    private Integer customerId;
    private String email;
    private String password;
    private String customerName;
    private String phone;
    private String address;
    private String gender;
    private LocalDate dob;
    private Timestamp createAt;
    private String avatar;
}
