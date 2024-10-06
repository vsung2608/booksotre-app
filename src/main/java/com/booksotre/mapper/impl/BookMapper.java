package com.booksotre.mapper.impl;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.BookModel;

import java.sql.ResultSet;

public class BookMapper implements IRowMapper<BookModel> {
    @Override
    public BookModel mapper(ResultSet rs) {
        return null;
    }
}
