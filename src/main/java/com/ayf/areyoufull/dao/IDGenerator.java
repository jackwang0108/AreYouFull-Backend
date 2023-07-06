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
    public void setOrderMapper(OrderMapper orderMapper){
        IDGenerator.orderMapper = orderMapper;
    }

    @Autowired
    public void setAccountMapper(AccountMapper accountMapper) {
        IDGenerator.accountMapper = accountMapper;
    }

    @Autowired
    public void setMerchandiseMapper(MerchandiseMapper merchandiseMapper) {
        IDGenerator.merchandiseMapper = merchandiseMapper;
    }

    @Autowired
    public void setShopMapper(ShopMapper shopMapper) {
        IDGenerator.shopMapper = shopMapper;
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
