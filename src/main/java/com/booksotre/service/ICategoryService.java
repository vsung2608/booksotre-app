package com.booksotre.service;

import com.booksotre.model.CategoryModel;

import java.util.List;

public interface ICategoryService {
    CategoryModel findById(int categoryId);
    List<CategoryModel> findAll();
}
