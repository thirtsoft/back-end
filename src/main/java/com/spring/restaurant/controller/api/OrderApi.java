package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface OrderApi {

    @PostMapping(value = APP_ROOT + "/products/create")
    ResponseEntity<Order> save(Order product);

    @PostMapping(value = APP_ROOT + "/products/createWithFileInFolder")
    ResponseEntity<Order> saveArticleWithFileInFolder(@RequestParam(name = "article") String product,
                                                      @RequestParam(name = "photoArticle") MultipartFile photoArticle) throws IOException;

    @PutMapping(value = APP_ROOT + "/products/update/{idArticle}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Order> updateProduct(@PathVariable("idArticle") Long id, @RequestBody Order product);

    @GetMapping(value = APP_ROOT + "/products/findById/{prodId}")
    ResponseEntity<Order> getAllProductById(@PathVariable("prodId") Long prodId);

    @GetMapping(value = APP_ROOT + "/products/all")
    ResponseEntity<List<Order>> getAllProducts();

    @GetMapping(value = APP_ROOT + "/products/searchAllArticleOrderByIdDesc")
    ResponseEntity<List<Order>> getAllArticlesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/products/photoProductInContext/{idArticle}")
    byte[] getPhotoProductInContext(@PathVariable("idArticle") Long id) throws Exception;

    @PostMapping(value = APP_ROOT + "/products/uploadProductPhotoInFolder/{id}")
    void uploadPhotoProductInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @GetMapping(value = APP_ROOT + "/allOrders")
    ResponseEntity<List<Order>> allOrders(@RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/category")
    ResponseEntity<List<Order>> getAllOrderByCategoryId(@RequestParam Long id, @RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/orderkey")
    ResponseEntity<List<Order>> getOrdersByKey(@RequestParam String word, @RequestParam int page, @RequestParam int size);

    @GetMapping(value = APP_ROOT + "/order")
    ResponseEntity<Order> getOrderById(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/orderSize")
    long orderSize();

    @GetMapping(value = APP_ROOT + "/ctegoryidsize")
    long getOrdersByIdCategorySize(@RequestParam Long id);

    @GetMapping(value = APP_ROOT + "/keysize")
    long sizeOfOrderByKey(@RequestParam String key);

    @DeleteMapping(value = APP_ROOT + "/products/delete/{idArticle}")
    void delete(@PathVariable("idArticle") Long id);

}
