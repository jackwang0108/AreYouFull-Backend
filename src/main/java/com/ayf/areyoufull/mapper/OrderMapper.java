package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer newOrder(@Param("order") Order order);
    Integer deleteByOrderID(@Param("orderID") Integer orderID);
    Integer deleteByUserID(@Param("userID") Integer userID);
    Integer deleteByDelivererID(@Param("delivererID") Integer delivererID);
    Integer deleteByShopID(@Param("shopID") Integer shopID);
    Integer updateOrder(@Param("order") Order order);
    Order findByOrderID(@Param("orderID") Integer orderID);
    List<Order> findByUserID(@Param("userID") Integer userID);
    List<Order> findByDelivererID(@Param("delivererID") Integer delivererID);
    List<Order> findByShopID(@Param("shopID") Integer shopID);
    List<Order> findByStatus(@Param("status") Byte status);
    Integer getNextID();
}
