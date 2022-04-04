package com.spring.restaurant.controller;

import com.spring.restaurant.model.Item;
import com.spring.restaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items/create")
    public ResponseEntity<Item> save(@RequestBody Item commandeDto) {
        Item requestOrder = itemService.save(commandeDto);
        return new ResponseEntity<>(requestOrder, HttpStatus.CREATED);
    }

    @GetMapping("/items/findById/{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") Long id) {
        Item requestOrder = itemService.findById(id);
        return new ResponseEntity<>(requestOrder, HttpStatus.OK);
    }

    @GetMapping("/items/all")
    public ResponseEntity<List<Item>> findAll() {
        List<Item> commandeDtoList = itemService.findAll();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @GetMapping("/items/searchAllItemsOrderByIdDesc")
    public ResponseEntity<List<Item>> getAllCommandesOrderByIdDesc() {
        List<Item> commandeDtoList = itemService.findByOrderByIdDesc();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @GetMapping("/items/searchAllItemsByRequestOrderId/{comId}")
    ResponseEntity<List<Item>> getAllItemsByRequestOrderId(@PathVariable("comId") Long comId) {
        List<Item> ligneCommandeDtoList = itemService.findListItemsByRequestOrderId(comId);
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }


    @GetMapping("/items/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
