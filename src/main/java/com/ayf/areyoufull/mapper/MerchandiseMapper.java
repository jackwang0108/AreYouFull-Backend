package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Merchandise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MerchandiseMapper {
    Integer newMerchandise(@Param("merchandise") Merchandise merchandise);
    Integer deleteByMerchandiseID(@Param("merchandiseID") Integer merchandiseID);
    Integer deleteByShopID(@Param("shopID") Integer shopID);
    Integer updateMerchandise(@Param("merchandise") Merchandise merchandise);
    List<Merchandise> findByMerchandiseID(@Param("merchandiseID") Integer merchandiseID);
    List<Merchandise> findByShopID(@Param("shopID") Integer shopID);
    Integer getNextID();
}
