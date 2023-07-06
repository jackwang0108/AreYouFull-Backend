package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopMapper {
    Integer newShop(@Param("shop") Shop shop);
    Integer deleteByShopID(@Param("shopID") Integer shopID);
    Integer updateShop(@Param("shop") Shop shop);
    Shop findByShopID(@Param("shopID") Integer shopID);
    Shop findByMerchantID(@Param("merchantID") Integer merchantID);
    Integer getNextID();
}
