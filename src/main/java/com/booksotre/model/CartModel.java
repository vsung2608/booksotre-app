package com.booksotre.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartModel {
    private Integer cartId;
    private Integer customerId;
    private Integer totalAmount;
    private Double totalPrice;
}
