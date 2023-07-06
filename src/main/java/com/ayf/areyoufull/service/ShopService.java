package com.ayf.areyoufull.service;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;

import java.util.List;

public interface ShopService {
    Shop getShopByID(Integer id);
    void newShop(Shop shop);
    void publish(List<Merchandise> merchandises);
    void remove(List<Integer> ids);
}
