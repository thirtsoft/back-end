package com.spring.restaurant.service;

import com.spring.restaurant.model.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    Order updateOrder(Long id, Order order);

    List<Order> findAllOrders();

    List<Order> findAllOrdersOrderDesc();

    List<Order> getAllOrders(int page, int size);

    List<Order> getOrdersByIdCategories(Long id, int page, int size);

    List<Order> getOrdersByKey(String key, int page, int size);

    Order getOrder(Long id);

    long getAllOrdersSize();

    long getOrdersByCategoryIdLength(Long id);

    long getOrderSizeByKey(String key);
}
