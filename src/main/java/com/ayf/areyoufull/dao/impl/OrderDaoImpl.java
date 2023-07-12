package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.OrderDetail;
import com.ayf.areyoufull.mapper.OrderDetailMapper;
import com.ayf.areyoufull.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Order findOrderByOrderID(Order order) {
        Order byOrderID = orderMapper.findByOrderID(order.getOrderID());
        if (byOrderID == null) return null;
        List<OrderDetail> details = orderDetailMapper.findByOrderID(byOrderID.getOrderID());
        byOrderID.setOrderDetail(details);
        return byOrderID;
    }

    @Override
    public List<Order> findOrderByStatus(Order order) {
        return orderMapper.findByStatus(order.getStatus());
    }

    @Override
    public List<Order> findUserOrderByStatus(Order order) {
        List<Order> byUserID = orderMapper.findByUserID(order.getUserID());
        List<Order> byStatus = new ArrayList<>();
        for (Order o : byUserID) {
            if (o.getStatus().equals(order.getStatus())){
                List<OrderDetail> details = orderDetailMapper.findByOrderID(o.getOrderID());
                o.setOrderDetail(details);
                byStatus.add(o);
            }
        }
        return byStatus;
    }

    @Override
    public List<Order> findShopOrderByStatus(Order order) {
        List<Order> byShopID = orderMapper.findByShopID(order.getShopID());
        List<Order> byStatus = new ArrayList<>();
        for (Order o : byShopID) {
            if (o.getStatus().equals(order.getStatus())){
                List<OrderDetail> details = orderDetailMapper.findByOrderID(o.getOrderID());
                o.setOrderDetail(details);
                byStatus.add(o);
            }
        }
        return byStatus;
    }

    @Override
    public List<Order> findDelivererOrderByStatus(Order order) {
        List<Order> byDelivererID = orderMapper.findByDelivererID(order.getDelivererID());
        List<Order> byStatus = new ArrayList<>();
        for (Order o : byDelivererID) {
            if (o.getStatus().equals(order.getStatus())){
                List<OrderDetail> details = orderDetailMapper.findByOrderID(o.getOrderID());
                o.setOrderDetail(details);
                byStatus.add(o);
            }
        }
        return byStatus;
    }
}
