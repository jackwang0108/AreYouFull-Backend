package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl implements ShopDao {
    private final ShopMapper shopMapper;

    @Autowired
    public ShopDaoImpl(ShopMapper shopMapper) {
        this.shopMapper = shopMapper;
    }
}
