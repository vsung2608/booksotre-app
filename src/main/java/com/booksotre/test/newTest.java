package com.booksotre.test;

import java.util.ArrayList;
import java.util.List;

import com.booksotre.model.CustomerModel;
import com.booksotre.model.EmployeeModel;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.CustomerService;
import com.booksotre.service.impl.EmployeeService;

public class newTest {
    public static void main(String[] args) {

        IEmployeeService employeeService = new EmployeeService();
        employeeService.createAccount(
                EmployeeModel.builder()
                        .email("quandaito123@gmail.com")
                        .password("")
                        .build()
        );

    }
}
