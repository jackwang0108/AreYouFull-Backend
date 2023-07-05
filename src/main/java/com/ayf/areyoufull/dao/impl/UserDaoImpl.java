package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.mapper.AccountMapper;
import com.ayf.areyoufull.mapper.AddressMapper;
import com.ayf.areyoufull.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public UserDaoImpl(UserMapper userMapper, AccountMapper accountMapper, AddressMapper addressMapper) {
        this.userMapper = userMapper;
        this.accountMapper = accountMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    public User findUserByID(Integer id) {
        User user = userMapper.findByID(id);
        Account account = accountMapper.findByID(id);
        user.setAccount(account);
        return user;
    }

    @Override
    public void newUser(User user) {
        accountMapper.newAccount(user.getAccount());
        userMapper.newUser(user);
    }
}
