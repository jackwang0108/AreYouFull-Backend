package com.ayf.areyoufull.service;

import com.ayf.areyoufull.entity.Merchandise;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.entity.Shop;
import com.ayf.areyoufull.entity.User;

import java.util.List;

public interface UserService {
    User getUserByUserID(Integer userID);
    void newUser(User user);
    void modifyUserInfo(User user);
    void terminateByUser(User user);
    List<Shop> browseShops(Integer amount);
    List<Merchandise> browseMerchandises(Integer shopID);
    Integer createOrder(Order order);
}

