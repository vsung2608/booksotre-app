package com.booksotre.model;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private Integer customerId;
    private String email;
    private String password;
    private String customerName;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    private Timestamp createAt;
}
