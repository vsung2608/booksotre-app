package com.booksotre.model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@Builder
public class OrderTamp {
    public static Integer customerId;
    public static Integer employeeId;
    public static Integer cartId;
    public static Integer bookId;
    public static Integer totalAmount;
    public static Double totalPrice = 0.0;
    public static String orderStatus;
    public static List<OrderDetailModel> listDetail = new ArrayList<>();
    public static String emailEmployee;
    public static String emailCustomer;
    public static Integer orderId;
    public static String role;
}
