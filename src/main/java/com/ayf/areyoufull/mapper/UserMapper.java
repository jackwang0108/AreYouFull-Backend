package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByID(@Param("userID") Integer userID);
    Integer newUser(@Param("user") User user);
}
