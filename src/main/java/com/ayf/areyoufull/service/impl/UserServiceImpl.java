package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.dao.ShopDao;
import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Order;
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
    private final OrderDao orderDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, ShopDao shopDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.shopDao = shopDao;
        this.orderDao = orderDao;
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

    @Override
    public List<Merchandise> browseMerchandises(Integer shopID) {
        return shopDao.getMerchandisesByShopID(shopID);
    }

    @Override
    public void createOrder(Order order) {
        orderDao.newOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }

    @Override
    public List<Order> querySelfOrderByStatus(Order order) {
        return orderDao.findUserOrderByStatus(order);
    }

    @Override
    public Order queryOrderByOrderID(Order order) {
        return orderDao.findOrderByOrderID(order);
    }
}
