package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Merchandise;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchandiseMapper {
    Merchandise findByID(Integer id);
    void newMerchandise(Merchandise merchandise);
    Integer getNextID();
}
