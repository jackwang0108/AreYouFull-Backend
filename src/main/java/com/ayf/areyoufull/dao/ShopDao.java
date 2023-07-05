package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;

public interface ShopDao {
    Shop findShopByID(Integer id);
    void newShop(Shop shop);
    void publishMerchandise(Merchandise merchandise);
    void removeMerchandiseByID(Integer id);
    Integer getNextID();
}