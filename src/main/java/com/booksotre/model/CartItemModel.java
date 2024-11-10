package com.booksotre.model;

import lombok.*;

@Data
@Builder
public class CartItemModel {
    private Integer cartItemId;
    private Integer cartId;
    private Integer bookId;
    private Integer quantity;
    private Double price;
}
