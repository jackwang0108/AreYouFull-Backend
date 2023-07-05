package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.AccountDao;
import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final AccountDao accountDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @Override
    public User getUserByID(Integer id) {
        Account accountDaoByID = accountDao.findByID(id);
        User userDaoByID = userDao.findByID(id);
        userDaoByID.setAccount(accountDaoByID);
        return userDaoByID;
    }

    @Override
    public void newUser(User user) {
        accountDao.newAccount(user.getAccount());
        userDao.newUser(user);
    }
}
