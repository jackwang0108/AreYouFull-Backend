package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Deliverer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DelivererMapper {
    Integer newDeliverer(@Param("deliverer") Deliverer deliverer);
    Integer deleteByDelivererID(@Param("delivererID") Integer delivererID);
    Integer updateDeliverer(@Param("deliverer") Deliverer deliverer);
    Deliverer findByDelivererID(@Param("delivererID") Integer delivererID);
}
