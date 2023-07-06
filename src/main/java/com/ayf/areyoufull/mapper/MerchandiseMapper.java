package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Merchandise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MerchandiseMapper {
    Merchandise findByID(@Param("merchandiseID") Integer merchandiseID);
    Integer newMerchandise(@Param("merchandise") Merchandise merchandise);
    Integer getNextID();
}
