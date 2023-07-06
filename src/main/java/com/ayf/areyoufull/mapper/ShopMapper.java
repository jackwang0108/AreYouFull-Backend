package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopMapper {
    Shop findByID(@Param("shopID") Integer shopID);
    Integer newShop(@Param("shop") Shop shop);
    Integer getNextID();
}
