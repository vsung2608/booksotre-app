package com.booksotre.service;

import java.util.List;

import com.booksotre.model.CategoryModel;

public interface ICategoryService {
    CategoryModel findById(int categoryId);

    List<CategoryModel> findAll();
}
