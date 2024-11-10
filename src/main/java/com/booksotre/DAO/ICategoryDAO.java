package com.booksotre.DAO;

import java.util.List;

import com.booksotre.model.CategoryModel;

public interface ICategoryDAO {
    CategoryModel findById(int id);

    List<CategoryModel> findAll();
}
