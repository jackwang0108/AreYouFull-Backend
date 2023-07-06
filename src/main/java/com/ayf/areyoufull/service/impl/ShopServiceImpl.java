package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public Shop getShopByID(Integer id) {
        return shopDao.findShopByID(id);
    }

    @Override
    public void newShop(Shop shop) {
        shopDao.newShop(shop);
    }

    @Override
    public void publish(List<Merchandise> merchandises) {
        merchandises.forEach(shopDao::publishMerchandise);
    }

    @Override
    public void remove(List<Integer> ids) {
        ids.forEach(shopDao::removeMerchandiseByID);
    }
}
