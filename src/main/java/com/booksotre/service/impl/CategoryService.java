package com.booksotre.service.impl;

import java.util.List;

import com.booksotre.DAO.impl.CategoryDAO;
import com.booksotre.model.CategoryModel;
import com.booksotre.service.ICategoryService;

public class CategoryService implements ICategoryService {

    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public CategoryModel findById(int categoryId) {
        return categoryDAO.findById(categoryId);
    }

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
