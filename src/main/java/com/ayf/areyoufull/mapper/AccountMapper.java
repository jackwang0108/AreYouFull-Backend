package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account findByID(Integer id);
}
