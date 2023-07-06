package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    Account findByID(@Param("accountID") Integer accountID);
    Integer newAccount(@Param("account") Account account);
    Integer getNextID();
}
