package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }
}
