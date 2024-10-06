package com.booksotre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
    private Integer bookID;
    private String bookName;
    private String isbn;
    private Double price;
    private Date publicationDate;
    private Integer authorID;
    private Integer publisherID;
    private Integer categoryID;
    private Integer stockQuantity;
    private String decription;
    private String image;
}
