package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ShopDao shopDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, ShopDao shopDao) {
        this.userDao = userDao;
        this.shopDao = shopDao;
    }

    @Override
    public User getUserByUserID(Integer userID) {
        return userDao.findUserByUserID(userID);
    }

    @Override
    public void newUser(User user) {
        userDao.newUser(user);
    }

    @Override
    public void modifyUserInfo(User user) {
        userDao.modifyUserInfo(user);
    }

    @Override
    public void terminateByUser(User user) {
        userDao.terminateByUser(user);
    }

    @Override
    public List<Shop> browseShops(Integer amount) {
        return shopDao.findRandomShopWithAmount(amount);
    }
}
