package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.CategoryAPI;
import com.spring.restaurant.model.Category;
import com.spring.restaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
//@RequestMapping("/api")
public class CategoryController implements CategoryAPI {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

   // @PostMapping("/categories/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category categoryResult = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryResult, HttpStatus.CREATED);
    }

  //  @PutMapping("/categories/update/{catId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("catId") Long catId, @RequestBody Category category) {
        category.setId(catId);
        Category categoryResult = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryResult, HttpStatus.OK);
    }

  //  @GetMapping("/categories/findById/{catId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("catId") Long catId) {
        Category categoryResult = categoryService.findCategoryById(catId);
        return new ResponseEntity<>(categoryResult, HttpStatus.OK);
    }

    // http://localhost:8080/api/allCategoies
  //  @GetMapping("/allCategoies")
    public ResponseEntity<List<Category>>  getAllCategory(){
        List<Category> categoryList = categoryService.allCategories();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

  //  @GetMapping("/categories/searchAllCategoriesOrderByIdDesc")
    public ResponseEntity<List<Category>> getAllCategoriesOrderDesc() {
        List<Category> categoryList = categoryService.findAllCategoriesOrderDesc();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    // @DeleteMapping("/categories/delete/{catId}")
    public void deleteCategoryById(@PathVariable("catId") Long catId) {
        categoryService.deleteCategory(catId);
    }

}