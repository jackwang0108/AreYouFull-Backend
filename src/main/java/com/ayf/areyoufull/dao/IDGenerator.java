package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDGenerator {
    private static OrderMapper orderMapper;
    private static AccountMapper accountMapper;
    private static MerchandiseMapper merchandiseMapper;
    private static ShopMapper shopMapper;
    private static AddressMapper addressMapper;

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

    @Autowired
    public void setAddressMapper(AddressMapper addressMapper){
        IDGenerator.addressMapper = addressMapper;
    }

    public static Integer getNextOrderID(){
        Integer nextID = orderMapper.getNextID();
        return nextID == null ? 1 : nextID;
    }

    public static Integer getNextAccountID(){
        Integer nextID = accountMapper.getNextID();
        return nextID == null || nextID < 10001 ? 1 : nextID;
    }

    public static Integer getMerchandiseNextID(){
        Integer nextID = merchandiseMapper.getNextID();
        return nextID == null ? 1 : nextID;
    }

    public static Integer getShopNextID(){
        Integer nextID = shopMapper.getNextID();
        return nextID == null ? 1 : nextID;
    }

    public static Integer getAddressNextID() {
        Integer nextID = addressMapper.getNextID();
        return nextID == null ? 1 : nextID;
    }
}
