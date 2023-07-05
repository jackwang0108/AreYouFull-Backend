package com.ayf.areyoufull.service;

import com.ayf.areyoufull.dao.AccountDao;
import com.ayf.areyoufull.dao.MerchandiseDao;
import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDService {
    private static OrderDao orderDao;
    private static AccountDao accountDao;
    private static MerchandiseDao merchandiseDao;
    private static ShopDao shopDao;

    @Autowired
    public void setOrderDao(OrderDao orderDao){
        IDService.orderDao = orderDao;
    }

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        IDService.accountDao = accountDao;
    }

    @Autowired
    public void setMerchandiseDao(MerchandiseDao merchandiseDao) {
        IDService.merchandiseDao = merchandiseDao;
    }

    @Autowired
    public void setShopDao(ShopDao shopDao) {
        IDService.shopDao = shopDao;
    }

    public static Integer getNextOrderID(){
        return orderDao.getNextID();
    }

    public static Integer getNextAccountID(){
        return accountDao.getNextID();
    }

    public static Integer getMerchandiseNextID(){
        return merchandiseDao.getNextID();
    }

    public static Integer getShopNextID(){
        return shopDao.getNextID();
    }
}
