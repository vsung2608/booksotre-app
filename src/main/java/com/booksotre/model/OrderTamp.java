package com.booksotre.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderTamp {
    public static Integer customerId;
    public static Integer employeeId;
    public static Integer totalAmount;
    public static Double totalPrice = 0.0;
    public static String orderStatus;
    public static List<OrderDetailModel> listDetail = new ArrayList<>();
    public static String emailEmployee;
    public static String emailCustomer;
}
