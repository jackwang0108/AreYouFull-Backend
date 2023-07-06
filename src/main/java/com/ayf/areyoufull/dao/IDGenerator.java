package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.mapper.AccountMapper;
import com.ayf.areyoufull.mapper.MerchandiseMapper;
import com.ayf.areyoufull.mapper.OrderMapper;
import com.ayf.areyoufull.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDGenerator {
    private static OrderMapper orderMapper;
    private static AccountMapper accountMapper;
    private static MerchandiseMapper merchandiseMapper;
    private static ShopMapper shopMapper;

    @Autowired
    public void setOrderDao(OrderMapper orderDao){
        IDGenerator.orderMapper = orderDao;
    }

    @Autowired
    public void setAccountDao(AccountMapper accountDao) {
        IDGenerator.accountMapper = accountDao;
    }

    @Autowired
    public void setMerchandiseDao(MerchandiseMapper merchandiseDao) {
        IDGenerator.merchandiseMapper = merchandiseDao;
    }

    @Autowired
    public void setShopDao(ShopMapper shopDao) {
        IDGenerator.shopMapper = shopDao;
    }

    public static Integer getNextOrderID(){
        return orderMapper.getNextID();
    }

    public static Integer getNextAccountID(){
        return accountMapper.getNextID();
    }

    public static Integer getMerchandiseNextID(){
        return merchandiseMapper.getNextID();
    }

    public static Integer getShopNextID(){
        return shopMapper.getNextID();
    }
}
