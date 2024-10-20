package com.booksotre.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private Integer categoryId;
    private String categoryName;
}
