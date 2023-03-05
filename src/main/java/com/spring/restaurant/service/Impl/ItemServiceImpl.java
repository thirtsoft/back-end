package com.spring.restaurant.service.Impl;

import com.spring.restaurant.deo.ItemRepository;
import com.spring.restaurant.model.Item;
import com.spring.restaurant.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item commandeDto) {
        return itemRepository.save(commandeDto);
    }

    public Item findById(Long id) {
        if (id == null) {
            log.error("RequestOrder Id is null");
            return null;
        }

        Optional<Item> commande = itemRepository.findById(id);

        return commande.get();

    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByOrderByIdDesc() {
        return itemRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Item> findListItemsOrderByQuantityDesc() {
        return itemRepository.findByOrderByQuantityDesc();
    }

    public List<Item> findListItemsByRequestOrderId(Long comId) {
        return itemRepository.ListItemByRequestOrderId(comId);
    }

    public void delete(Long id) {
        if (id == null) {
            log.error("Commande Id is null");
            return;
        }
        itemRepository.deleteById(id);

    }

}
