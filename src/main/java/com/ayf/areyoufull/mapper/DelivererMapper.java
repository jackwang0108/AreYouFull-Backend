package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Deliverer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DelivererMapper {
    Deliverer findByID(@Param("delivererID") Integer delivererID);
    Integer newDeliverer(@Param("deliverer") Deliverer deliverer);
}
