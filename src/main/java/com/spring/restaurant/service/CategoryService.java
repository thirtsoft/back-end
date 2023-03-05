package com.spring.restaurant.service;

import com.spring.restaurant.model.Category;

import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);

    Category updateCategory(Long catId, Category category);

    Category findCategoryById(Long id);

    List<Category> allCategories();

    List<Category> findAllCategoriesOrderDesc();

    void deleteCategory(Long catId);
}
