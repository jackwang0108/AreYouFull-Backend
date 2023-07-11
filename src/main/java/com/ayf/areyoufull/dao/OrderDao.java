package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Order;

import java.util.List;

public interface OrderDao {
    void newOrder(Order order);
    void updateOrder(Order order);
    Order findOrderByOrderID(Order order);
    List<Order> findOrderByStatus(Order order);
    List<Order> findUserOrderByStatus(Order order);
    List<Order> findShopOrderByStatus(Order order);
    List<Order> findDelivererOrderByStatus(Order order);
}
