package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Order findByID(Integer id);
    void newOrder(Order order);
    Integer getNextID();
}
