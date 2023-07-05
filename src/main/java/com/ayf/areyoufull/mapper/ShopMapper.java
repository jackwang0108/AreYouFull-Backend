package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    Shop findByID(Integer id);
    void newShop(Shop shop);
    Integer getNextID();
}
