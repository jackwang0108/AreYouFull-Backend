package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;

import java.util.List;

public interface ShopDao {
    Shop findShopByID(Integer id);
    void newShop(Shop shop);
    void publishMerchandise(Merchandise merchandise);
    void removeMerchandiseByID(Integer id);
    List<Merchandise> merchandisesOfShop(Integer shopID);
    Integer getNextID();
}