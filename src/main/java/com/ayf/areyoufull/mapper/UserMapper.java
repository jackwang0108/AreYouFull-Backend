package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    Integer newUser(@Param("user") User user);
    Integer deleteByUserID(@Param("userID") Integer userID);
    Integer updateUser(@Param("user") User user);
    User findByID(@Param("userID") Integer userID);
}
