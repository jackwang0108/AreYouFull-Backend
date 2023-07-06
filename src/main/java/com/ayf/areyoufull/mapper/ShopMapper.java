package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopMapper {
    Integer newShop(@Param("shop") Shop shop);
    Integer deleteByShopID(@Param("shopID") Integer shopID);
    Integer updateShop(@Param("shop") Shop shop);
    Shop findByShopID(@Param("shopID") Integer shopID);
    Shop findByMerchantID(@Param("merchantID") Integer merchantID);
    List<Shop> findRandomShopWithAmount(Integer amount);
    Integer getNextID();
}
