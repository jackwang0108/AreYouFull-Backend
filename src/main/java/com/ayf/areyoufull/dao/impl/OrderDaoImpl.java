package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.OrderDetail;
import com.ayf.areyoufull.mapper.OrderDetailMapper;
import com.ayf.areyoufull.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDaoImpl(OrderMapper orderMapper, OrderDetailMapper orderDetailMapper) {
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public void newOrder(Order order) {
        orderMapper.newOrder(order);
        order.getOrderDetail().forEach(orderDetailMapper::newOrderDetail);
    }

    @Override
    public void updateOrder(Order order) {
        if (order.getOrderDetail() != null)
            order.getOrderDetail().forEach(orderDetailMapper::updateOrderDetail);
        orderMapper.updateOrder(order);
    }
}
