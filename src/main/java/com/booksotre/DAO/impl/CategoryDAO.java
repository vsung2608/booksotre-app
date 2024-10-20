package com.booksotre.DAO.impl;

import java.util.List;

import com.booksotre.DAO.ICategoryDAO;
import com.booksotre.mapper.impl.CategoryMapper;
import com.booksotre.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
    @Override
    public CategoryModel findById(int id) {
        String query = "SELECT category_id, category_name FROM Category WHERE category_id = ?";
        List<CategoryModel> list = query(query, new CategoryMapper());
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public List<CategoryModel> findAll() {
        String query = "SELECT category_id, category_name FROM Category";
        return query(query, new CategoryMapper());
    }
}
