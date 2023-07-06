package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    Order findByID(@Param("orderID") Integer orderID);
    Integer newOrder(@Param("order") Order order);
    Integer getNextID();
}
