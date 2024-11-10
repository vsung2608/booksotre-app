package com.booksotre.model;

import lombok.*;

@Data
@Builder
public class CartModel {
    private Integer cartId;
    private Integer customerId;
    private Integer totalAmount;
    private Double totalPrice;
}
