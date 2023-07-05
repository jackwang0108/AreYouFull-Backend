package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.User;

public interface UserDao {
    User findByID(Integer id);
    void newUser(User user);
}
