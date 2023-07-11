package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopDao shopDao;
    private final OrderDao orderDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao, OrderDao orderDao) {
        this.shopDao = shopDao;
        this.orderDao = orderDao;
    }

    @Override
    public Shop getShopByShopID(Integer shopID) {
        return shopDao.findShopByShopID(shopID);
    }

    @Override
    public Shop getShopByMerchantID(Integer merchantID) {
        return shopDao.findShopByMerchantID(merchantID);
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
        ids.forEach(shopDao::removeMerchandiseByMerchandiseID);
    }

    @Override
    public List<Merchandise> getMerchandisesByShopID(Integer shopID) {
        return shopDao.getMerchandisesByShopID(shopID);
    }

    @Override
    public List<Merchandise> getMerchandisesByMerchantID(Integer merchantID) {
        return shopDao.getMerchandisesByMerchantID(merchantID);
    }

    @Override
    public void modifyShopInfo(Shop shop) {
        shopDao.modifyShopInfo(shop);
    }

    @Override
    public void terminateShop(Shop shop) {
        shopDao.terminateByShop(shop);
    }

    @Override
    public Order queryOrderByOrderID(Order order) {
        return orderDao.findOrderByOrderID(order);
    }

    @Override
    public List<Order> queryOrderByStatus(Order order) {
        return orderDao.findOrderByStatus(order);
    }

    @Override
    public List<Order> querySelfOrderByStatus(Order order) {
        return orderDao.findShopOrderByStatus(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }
}
