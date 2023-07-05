package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserMapper userMapper;

    @Autowired
    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByID(Integer id) {
        return userMapper.findByID(id);
    }

    @Override
    public void newUser(User user) {
        userMapper.newUser(user);
    }
}
