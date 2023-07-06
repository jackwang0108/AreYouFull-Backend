package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.mapper.AccountMapper;
import com.ayf.areyoufull.mapper.MerchandiseMapper;
import com.ayf.areyoufull.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl implements ShopDao {
    private final ShopMapper shopMapper;
    private final AccountMapper accountMapper;
    private final MerchandiseMapper merchandiseMapper;

    @Autowired
    public ShopDaoImpl(ShopMapper shopMapper, AccountMapper accountMapper, MerchandiseMapper merchandiseMapper) {
        this.shopMapper = shopMapper;
        this.accountMapper = accountMapper;
        this.merchandiseMapper = merchandiseMapper;
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
    public Integer getNextID() {
        return shopMapper.getNextID();
    }
}
