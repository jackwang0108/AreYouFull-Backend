package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final OrderMapper orderMapper;

    @Autowired
    public OrderDaoImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public Integer getNextID() {
        return orderMapper.getNextID();
    }
}
