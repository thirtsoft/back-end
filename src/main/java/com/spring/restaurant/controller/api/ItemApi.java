package com.spring.restaurant.controller.api;

import com.spring.restaurant.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface ItemApi {

    @PostMapping(value = APP_ROOT + "/items/create")
    ResponseEntity<Item> save(@RequestBody Item commandeDto);

    @GetMapping(value = APP_ROOT + "/items/findById/{id}")
    ResponseEntity<Item> getItemById(@PathVariable("id") Long id);

    @GetMapping(value = APP_ROOT + "/items/all")
    ResponseEntity<List<Item>> getAllItems();

    @GetMapping(value = APP_ROOT + "/items/searchAllItemsOrderByIdDesc")
    ResponseEntity<List<Item>> getAllCommandesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/items/searchAllItemsOrderByQuantityDesc")
    ResponseEntity<List<Item>> getAllItemsOrderByQuantityesc();

    @GetMapping(value = APP_ROOT + "/items/searchAllItemsByRequestOrderId/{comId}")
    ResponseEntity<List<Item>> getAllItemsByRequestOrderId(@PathVariable("comId") Long comId);

    @GetMapping(value = APP_ROOT + "/items/delete/{id}")
    void delete(@PathVariable("id") Long id);
}
