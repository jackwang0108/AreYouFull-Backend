package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {
    private final ShopMapper shopMapper;
    private final AccountMapper accountMapper;
    private final MerchandiseMapper merchandiseMapper;
    private final AddressMapper addressMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public ShopDaoImpl(ShopMapper shopMapper, AccountMapper accountMapper, MerchandiseMapper merchandiseMapper, AddressMapper addressMapper, OrderMapper orderMapper) {
        this.shopMapper = shopMapper;
        this.accountMapper = accountMapper;
        this.merchandiseMapper = merchandiseMapper;
        this.addressMapper = addressMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Shop findShopByID(Integer id) {
        Shop shop = shopMapper.findByID(id);
        Account account = accountMapper.findByID(shop.getMerchantID());
        shop.setAccount(account);
        return shop;
    }

    @Override
    public void newShop(Shop shop) {
        accountMapper.newAccount(shop.getAccount());
        shopMapper.newShop(shop);
    }

    @Override
    public void publishMerchandise(Merchandise merchandise) {
        merchandiseMapper.newMerchandise(merchandise);
    }

    @Override
    public void removeMerchandiseByID(Integer id) {
    }

    @Override
    public List<Merchandise> merchandisesOfShop(Integer shopID) {
        return merchandiseMapper.findByShopID(shopID);
    }

    @Override
    public Integer getNextID() {
        return shopMapper.getNextID();
    }
}
