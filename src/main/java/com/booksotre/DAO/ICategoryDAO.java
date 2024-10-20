package com.booksotre.DAO;

import com.booksotre.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO {
    CategoryModel findById(int id);
    List<CategoryModel> findAll();
}
