package com.booksotre.test;

import com.booksotre.model.EmployeeModel;
import com.booksotre.service.IEmployeeService;
import com.booksotre.service.impl.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class fakeData {
    public static void main(String[] args) {

        IEmployeeService employeeService = new EmployeeService();

        List<EmployeeModel> employees = new ArrayList<>();

        // Tạo 10 bản ghi EmployeeModel
        employees.add(EmployeeModel.builder()
                .email("alice.johnson@example.com")
                .password("password123")
                .employeeName("Alice Johnson")
                .phone("0987654321")
                .address("123 Main St, City A")
                .gender("F")
                .dob("1990-01-15")
                .role("Manager")
                .salary(75000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("bob.smith@example.com")
                .password("password456")
                .employeeName("Bob Smith")
                .phone("0978543210")
                .address("456 Elm St, City B")
                .gender("M")
                .dob("1988-07-23")
                .role("Cashier")
                .salary(45000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("charlie.davis@example.com")
                .password("password789")
                .employeeName("Charlie Davis")
                .phone("0934567891")
                .address("789 Oak St, City C")
                .gender("M")
                .dob("1995-11-11")
                .role("Salesperson")
                .salary(50000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("diana.martin@example.com")
                .password("password321")
                .employeeName("Diana Martin")
                .phone("0912345678")
                .address("321 Pine St, City D")
                .gender("F")
                .dob("1992-04-05")
                .role("Accountant")
                .salary(60000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("ethan.brown@example.com")
                .password("password654")
                .employeeName("Ethan Brown")
                .phone("0908765432")
                .address("654 Maple St, City E")
                .gender("M")
                .dob("1985-09-22")
                .role("Supervisor")
                .salary(70000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("fiona.white@example.com")
                .password("password987")
                .employeeName("Fiona White")
                .phone("0998765432")
                .address("987 Birch St, City F")
                .gender("F")
                .dob("1993-03-10")
                .role("HR")
                .salary(55000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("george.wilson@example.com")
                .password("password234")
                .employeeName("George Wilson")
                .phone("0954321897")
                .address("432 Cedar St, City G")
                .gender("M")
                .dob("1991-12-18")
                .role("IT Support")
                .salary(48000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("hannah.jones@example.com")
                .password("password567")
                .employeeName("Hannah Jones")
                .phone("0945678234")
                .address("567 Walnut St, City H")
                .gender("F")
                .dob("1994-06-14")
                .role("Sales Manager")
                .salary(65000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("ian.miller@example.com")
                .password("password890")
                .employeeName("Ian Miller")
                .phone("0923456789")
                .address("890 Spruce St, City I")
                .gender("M")
                .dob("1987-08-25")
                .role("Logistics")
                .salary(52000.00)
                .build());

        employees.add(EmployeeModel.builder()
                .email("julia.morris@example.com")
                .password("password432")
                .employeeName("Julia Morris")
                .phone("0987345612")
                .address("432 Cherry St, City J")
                .gender("F")
                .dob("1996-05-30")
                .role("Assistant Manager")
                .salary(68000.00)
                .build());

        // In thông tin của 10 nhân viên đã tạo
        // employees.forEach(employeeService::createAccount);
    }
}
