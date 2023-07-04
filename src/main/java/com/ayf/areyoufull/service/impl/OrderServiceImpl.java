package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
