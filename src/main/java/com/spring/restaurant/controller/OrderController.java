package com.spring.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.restaurant.controller.api.OrderApi;
import com.spring.restaurant.model.Order;
import com.spring.restaurant.service.OrderService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


// http://localhost:8080/api

@RestController
@CrossOrigin("http://localhost:4200")
//@RequestMapping("/api")
public class OrderController implements OrderApi {

    @Autowired
    ServletContext context;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //  @PostMapping("/products/create")
    public ResponseEntity<Order> save(Order product) {
        Order productResult = orderService.saveOrder(product);
        return new ResponseEntity<>(productResult, HttpStatus.CREATED);
    }

    //  @PostMapping("/products/createWithFileInFolder")
    public ResponseEntity<Order> saveArticleWithFileInFolder(@RequestParam(name = "article") String product,
                                                             @RequestParam(name = "photoArticle") MultipartFile photoArticle) throws IOException {
        Order articleDto = new ObjectMapper().readValue(product, Order.class);
        if (photoArticle != null && !photoArticle.isEmpty()) {
            String filename = photoArticle.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/PhotoProducts/" + File.separator + newFileName));
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, photoArticle.getBytes());
            articleDto.setImg(filename);
        }
        Order productResultFinal = orderService.saveOrder(articleDto);
        return new ResponseEntity<>(productResultFinal, HttpStatus.CREATED);
    }

    /*  @PutMapping( value = "/products/update/{idArticle}",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
     */
    public ResponseEntity<Order> updateProduct(@PathVariable("idArticle") Long id, @RequestBody Order product) {
        product.setId(id);
        Order productResultFinal = orderService.saveOrder(product);
        return new ResponseEntity<>(productResultFinal, HttpStatus.OK);
    }

    // @GetMapping(value = "/products/findById/{prodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getAllProductById(@PathVariable("prodId") Long prodId) {
        Order orderList = orderService.getOrder(prodId);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // @GetMapping(value = "/products/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllProducts() {
        List<Order> orderList = orderService.findAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    //  @GetMapping(value = "/products/searchAllArticleOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllArticlesOrderByIdDesc() {
        List<Order> orderList = orderService.findAllOrdersOrderDesc();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    //  @GetMapping(value = "/products/photoProductInContext/{idArticle}")
    public byte[] getPhotoProductInContext(@PathVariable("idArticle") Long id) throws Exception {
        Order product = orderService.getOrder(id);
     //   ServletContext context = null;
        return Files.readAllBytes(Paths.get(context.getRealPath("/PhotoProducts/") + product.getImg()));
    }


    //  @PostMapping(path =  "/products/uploadProductPhotoInFolder/{id}")
    public void uploadPhotoProductInFolder(MultipartFile file, @PathVariable("id") Long id) throws IOException {
        Order product = orderService.getOrder(id);
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
     //   ServletContext context = null;
        File serverFile = new File(context.getRealPath("/PhotoProducts/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
            product.setImg(filename);
            orderService.saveOrder(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // http://localhost:8080/api/allOrders?page={value}&size={value}
    // @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> allOrders(@RequestParam int page, @RequestParam int size) {
        List<Order> orderList = orderService.getAllOrders(page, size);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // http://localhost:8080/api/category?id={value}&page={value}&size={value}
    // @GetMapping("/category")
    public ResponseEntity<List<Order>> getAllOrderByCategoryId(@RequestParam Long id, @RequestParam int page, @RequestParam int size) {
        List<Order> orderList = orderService.getOrdersByIdCategories(id, page, size);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // http://localhost:8080/api/orderkey?word=key&page={value}&size={value}
    // @GetMapping("/orderkey")
    public ResponseEntity<List<Order>> getOrdersByKey(@RequestParam String word, @RequestParam int page, @RequestParam int size) {
        List<Order> orderList = orderService.getOrdersByKey(word, page, size);
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    // http://localhost:8080/api/order?id={value}
    // @GetMapping("/order")
    public ResponseEntity<Order> getOrderById(@RequestParam Long id) {
        Order orderSearch = orderService.getOrder(id);
        return new ResponseEntity<>(orderSearch, HttpStatus.OK);
    }

    // http://localhost:8080/api/orderSize
    //  @GetMapping("/orderSize")
    public long orderSize() {
        return orderService.getAllOrdersSize();
    }

    // http://localhost:8080/api/ctegoryidsize?id={value}
    // @GetMapping("/ctegoryidsize")
    public long getOrdersByIdCategorySize(@RequestParam Long id) {
        return orderService.getOrdersByCategoryIdLength(id);
    }

    // http://localhost:8080/api/keysize?key={value}
    // @GetMapping("/keysize")
    public long sizeOfOrderByKey(@RequestParam String key) {
        return orderService.getOrderSizeByKey(key);
    }

    //  @DeleteMapping(value = "/products/delete/{idArticle}")
    public void delete(@PathVariable("idArticle") Long id) {

    }
}
