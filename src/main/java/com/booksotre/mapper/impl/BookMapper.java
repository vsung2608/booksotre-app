package com.booksotre.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.BookModel;

public class BookMapper implements IRowMapper<BookModel> {
    @Override
    public BookModel mapper(ResultSet rs) {
        BookModel book;
        try {
            book = BookModel.builder()
                    .bookId(rs.getInt("book_id"))
                    .title(rs.getString("title"))
                    .isbn(rs.getString("isbn"))
                    .author(rs.getString("author"))
                    .publisher(rs.getString("publisher"))
                    .categoryId(rs.getInt("category_id"))
                    .quantity(rs.getInt("quantity"))
                    .price(rs.getBigDecimal("price"))
                    .image(rs.getString("image"))
                    .status((rs.getInt("quantity")) > 0 ? "Còn hàng" : "Hết hàng")
                    .description(rs.getString("description"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }
}
