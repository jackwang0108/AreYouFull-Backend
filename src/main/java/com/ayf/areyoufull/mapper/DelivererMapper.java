package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Deliverer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DelivererMapper {
    Deliverer findByID(Integer id);
    void newDeliverer(Deliverer deliverer);
}
