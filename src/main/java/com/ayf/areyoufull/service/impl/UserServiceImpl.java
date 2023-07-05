package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByID(Integer id) {
        return userDao.findUserByID(id);
    }

    @Override
    public void newUser(User user) {
        userDao.newUser(user);
    }
}
