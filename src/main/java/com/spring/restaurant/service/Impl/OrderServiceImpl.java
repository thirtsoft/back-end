package com.spring.restaurant.service.Impl;

import com.spring.restaurant.deo.OrderRepository;
import com.spring.restaurant.model.Order;
import com.spring.restaurant.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            log.error("Product not found");
        }
        Order updateOrder = orderRepository.findById(id).get();
        updateOrder.setName(order.getName());
        updateOrder.setPrice(order.getPrice());
        updateOrder.setQuantity(order.getQuantity());
        updateOrder.setImg(order.getImg());
        updateOrder.setDescription(order.getDescription());
        updateOrder.setCategory(order.getCategory());

        return orderRepository.save(updateOrder);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findAllOrdersOrderDesc() {
        return orderRepository.findByOrderByIdDesc();
    }

    public List<Order> getAllOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable).getContent();
    }

    public List<Order> getOrdersByIdCategories(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByCategoryId(id, pageable).getContent();
    }

    public List<Order> getOrdersByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findByNameContaining(key, pageable).getContent();
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    public long getAllOrdersSize() {
        return orderRepository.count();
    }

    public long getOrdersByCategoryIdLength(Long id) {
        return orderRepository.getOrderLengthByCategoryId(id);
    }

    public long getOrderSizeByKey(String key) {
        return orderRepository.getOrderSizeByKey(key);
    }


}
