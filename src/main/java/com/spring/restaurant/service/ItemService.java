package com.spring.restaurant.service;

import com.spring.restaurant.model.Item;

import java.util.List;


public interface ItemService {

    Item save(Item commandeDto);

    Item findById(Long id);

    List<Item> findAll();

    List<Item> findByOrderByIdDesc();

    List<Item> findListItemsOrderByQuantityDesc();

    List<Item> findListItemsByRequestOrderId(Long comId);

    void delete(Long id);

}
