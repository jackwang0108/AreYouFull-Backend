package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByID(Integer id);

}
