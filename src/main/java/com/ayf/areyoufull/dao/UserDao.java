package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.User;

public interface UserDao {
    User findUserByUserID(Integer userID);
    void newUser(User user);
    void modifyUserInfo(User user);
    void terminateByUser(User user);
}
