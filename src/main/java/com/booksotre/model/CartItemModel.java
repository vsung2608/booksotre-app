package com.booksotre.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemModel {
    private Integer cartItemId;
    private Integer cartId;
    private Integer bookId;
    private Integer quantity;
}
