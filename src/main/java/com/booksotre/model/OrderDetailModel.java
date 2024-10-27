package com.booksotre.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer bookId;
    private Integer quantity;
    private Double price;
}
