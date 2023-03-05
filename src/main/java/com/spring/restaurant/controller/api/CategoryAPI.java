package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface CategoryAPI {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Category> createCategory(@RequestBody Category category);

    @PutMapping(value = APP_ROOT + "/categories/update/{catId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Category> updateCategory(@PathVariable("catId") Long catId, @RequestBody Category category);

    @GetMapping(value = APP_ROOT + "/categories/findById/{catId}",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Category> getCategoryById(@PathVariable("catId") Long catId);

    // http://localhost:8080/api/allCategoies
    @GetMapping(value = APP_ROOT + "/categories/allCategories")
    ResponseEntity<List<Category>>  getAllCategory();

    @GetMapping(value = APP_ROOT + "/categories/searchAllCategoriesOrderByIdDesc")
    ResponseEntity<List<Category>> getAllCategoriesOrderDesc();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{catId}")
    void deleteCategoryById(@PathVariable("catId") Long catId);


}
