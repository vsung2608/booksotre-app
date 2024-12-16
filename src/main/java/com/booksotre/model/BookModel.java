package com.booksotre.model;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {
    private Integer bookId;
    private Integer categoryId;
    private String title;
    private String isbn;
    private String author;
    private String publisher;
    private Double price;
    private Integer quantity;
    private String description;
    private String image;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return Objects.equals(categoryId, bookModel.categoryId)
                && Objects.equals(title, bookModel.title)
                && Objects.equals(isbn, bookModel.isbn)
                && Objects.equals(author, bookModel.author)
                && Objects.equals(publisher, bookModel.publisher)
                && Objects.equals(price, bookModel.price)
                && Objects.equals(quantity, bookModel.quantity)
                && Objects.equals(description, bookModel.description)
                && Objects.equals(image, bookModel.image)
                && Objects.equals(status, bookModel.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, title, isbn, author, publisher, price, quantity, description, image, status);
    }
}
