package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    Integer newOrderDetail(@Param("orderDetail") OrderDetail orderDetail);
    Integer deleteByOrderDetail(@Param("orderDetail") OrderDetail orderDetail);
    Integer updateOrderDetail(@Param("orderDetail") OrderDetail orderDetail);
    List<OrderDetail> findByOrderID(@Param("orderID") Integer orderID);
}
