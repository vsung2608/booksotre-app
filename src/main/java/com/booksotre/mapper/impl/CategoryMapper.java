package com.booksotre.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.booksotre.mapper.IRowMapper;
import com.booksotre.model.CategoryModel;

public class CategoryMapper implements IRowMapper<CategoryModel> {

    @Override
    public CategoryModel mapper(ResultSet rs) {
        CategoryModel category;
        try {
            category = CategoryModel.builder()
                    .categoryId(rs.getInt("category_id"))
                    .categoryName(rs.getString("category_name"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}
