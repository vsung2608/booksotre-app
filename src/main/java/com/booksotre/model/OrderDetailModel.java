package com.booksotre.model;

import lombok.*;

@Data
@Builder
public class OrderDetailModel {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer bookId;
    private Integer quantity;
    private Double price;
}
