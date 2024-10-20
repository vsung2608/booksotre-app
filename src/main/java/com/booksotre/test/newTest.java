package com.booksotre.test;

import com.booksotre.model.CustomerModel;
import com.booksotre.service.IBookService;
import com.booksotre.service.ICustomerService;
import com.booksotre.service.impl.BookService;
import com.booksotre.service.impl.CustomerService;

import java.util.ArrayList;
import java.util.List;

public class newTest {
    public static void main(String[] args) {

        ICustomerService customerService = new CustomerService();

        List<CustomerModel> list = new ArrayList<CustomerModel>();

        CustomerModel customer1 = CustomerModel.builder()
                .email("john.doe@example.com")
                .password("password123")
                .customerName("John Doe")
                .phone("0987654321")
                .address("123 Main St, City A")
                .gender("M")
                .dob("1990")
                .build();

        CustomerModel customer2 = CustomerModel.builder()
                .email("jane.smith@example.com")
                .password("password456")
                .customerName("Jane Smith")
                .phone("0978543210")
                .address("456 Elm St, City B")
                .gender("F")
                .dob("1992")
                .build();

        CustomerModel customer3 = CustomerModel.builder()
                .email("mark.jones@example.com")
                .password("password789")
                .customerName("Mark Jones")
                .phone("0934567891")
                .address("789 Oak St, City C")
                .gender("M")
                .dob("1988")
                .build();

        CustomerModel customer4 = CustomerModel.builder()
                .email("lisa.brown@example.com")
                .password("password321")
                .customerName("Lisa Brown")
                .phone("0912345678")
                .address("321 Pine St, City D")
                .gender("F")
                .dob("1995")
                .build();

        CustomerModel customer5 = CustomerModel.builder()
                .email("paul.green@example.com")
                .password("password654")
                .customerName("Paul Green")
                .phone("0908765432")
                .address("654 Maple St, City E")
                .gender("M")
                .dob("1985")
                .build();

        CustomerModel customer6 = CustomerModel.builder()
                .email("nancy.white@example.com")
                .password("password987")
                .customerName("Nancy White")
                .phone("0998765432")
                .address("987 Birch St, City F")
                .gender("F")
                .dob("1993")
                .build();

        CustomerModel customer7 = CustomerModel.builder()
                .email("david.black@example.com")
                .password("password234")
                .customerName("David Black")
                .phone("0954321897")
                .address("432 Cedar St, City G")
                .gender("M")
                .dob("1991")
                .build();

        CustomerModel customer8 = CustomerModel.builder()
                .email("emma.miller@example.com")
                .password("password567")
                .customerName("Emma Miller")
                .phone("0945678234")
                .address("567 Walnut St, City H")
                .gender("F")
                .dob("1994")
                .build();

        CustomerModel customer9 = CustomerModel.builder()
                .email("chris.wilson@example.com")
                .password("password890")
                .customerName("Chris Wilson")
                .phone("0923456789")
                .address("890 Spruce St, City I")
                .gender("M")
                .dob("1987")
                .build();

        CustomerModel customer10 = CustomerModel.builder()
                .email("susan.moore@example.com")
                .password("password432")
                .customerName("Susan Moore")
                .phone("0987345612")
                .address("432 Cherry St, City J")
                .gender("F")
                .dob("1996")
                .build();

        list.add(customer1);
        list.add(customer2);
        list.add(customer3);
        list.add(customer4);
        list.add(customer5);
        list.add(customer6);
        list.add(customer7);
        list.add(customer8);
        list.add(customer9);
        list.add(customer10);

    }
}
