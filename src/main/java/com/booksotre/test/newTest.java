package com.booksotre.test;

import java.util.ArrayList;
import java.util.List;

import com.booksotre.model.CustomerModel;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.impl.CustomerService;

public class newTest {
    public static void main(String[] args) {

        ICustomerService customerService = new CustomerService();

        List<CustomerModel> list = new ArrayList<CustomerModel>();

        CustomerModel customer1 = CustomerModel.builder()
                .email("john.doe@example.com")
                .password("password123")
                .build();

        CustomerModel customer2 = CustomerModel.builder()
                .email("jane.smith@example.com")
                .password("password456")
                .build();

        CustomerModel customer3 = CustomerModel.builder()
                .email("mark.jones@example.com")
                .password("password789")
                .build();

        CustomerModel customer4 = CustomerModel.builder()
                .email("lisa.brown@example.com")
                .password("password321")
                .build();

        CustomerModel customer5 = CustomerModel.builder()
                .email("paul.green@example.com")
                .password("password654")
                .build();

        CustomerModel customer6 = CustomerModel.builder()
                .email("nancy.white@example.com")
                .password("password987")
                .build();

        CustomerModel customer7 = CustomerModel.builder()
                .email("david.black@example.com")
                .password("password234")
                .build();

        CustomerModel customer8 = CustomerModel.builder()
                .email("emma.miller@example.com")
                .password("password567")
                .build();

        CustomerModel customer9 = CustomerModel.builder()
                .email("chris.wilson@example.com")
                .password("password890")
                .build();

        CustomerModel customer10 = CustomerModel.builder()
                .email("susan.moore@example.com")
                .password("password432")
                .build();
    }
}
