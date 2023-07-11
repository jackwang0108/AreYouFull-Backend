package com.ayf.areyoufull.service;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Shop;

import java.util.List;

public interface ShopService {
    Shop getShopByShopID(Integer shopID);
    Shop getShopByMerchantID(Integer merchantID);
    void newShop(Shop shop);
    void publish(List<Merchandise> merchandises);
    void remove(List<Integer> ids);
    List<Merchandise> getMerchandisesByShopID(Integer shopID);
    List<Merchandise> getMerchandisesByMerchantID(Integer merchantID);
    void modifyShopInfo(Shop shop);
    void terminateShop(Shop shop);
    Order queryOrderByOrderID(Order order);
    List<Order> queryOrderByStatus(Order order);
    List<Order> querySelfOrderByStatus(Order order);
    void updateOrder(Order order);
}
