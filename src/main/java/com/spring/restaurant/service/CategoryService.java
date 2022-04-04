package com.spring.restaurant.service;

import com.spring.restaurant.deo.CategoryRepository;
import com.spring.restaurant.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public Category updateCategory(Long catId, Category category) {
        if (!categoryRepository.existsById(catId)) {
            log.error("Category not found");
        }

        Category updateCategory = categoryRepository.findById(catId).get();
        updateCategory.setName(category.getName());
        updateCategory.setLogo(category.getLogo());

        return categoryRepository.save(updateCategory);

    }

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> findAllCategoriesOrderDesc() {
        return categoryRepository.findByOrderByIdDesc();
    }

    public void deleteCategory(Long catId) {
        if (catId == null) {
            log.error("Category not found");
        }
        categoryRepository.deleteById(catId);
    }
}
