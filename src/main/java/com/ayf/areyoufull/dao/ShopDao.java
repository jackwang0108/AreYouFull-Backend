package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;

import java.util.List;

public interface ShopDao {
    void newShop(Shop shop);
    void publishMerchandise(Merchandise merchandise);
    void removeMerchandiseByMerchandiseID(Integer merchandiseID);
    void removeAllFromShop(Integer shopID);
    List<Merchandise> getMerchandisesByShopID(Integer shopID);
    List<Merchandise> getMerchandisesByMerchantID(Integer merchantID);
    Shop findShopByShopID(Integer shopID);
    Shop findShopByMerchantID(Integer merchantID);
    Integer getNextID();
    void modifyShopInfo(Shop shop);
    void terminateByShop(Shop shop);
    List<Shop> findRandomShopWithAmount(Integer amount);
}