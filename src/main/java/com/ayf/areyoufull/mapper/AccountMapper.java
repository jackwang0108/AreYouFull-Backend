package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    Integer newAccount(@Param("account") Account account);
    Integer deleteByAccountID(@Param("accountID") Integer accountID);
    Integer updateAccount(@Param("account") Account account);
    Account findByAccountID(@Param("accountID") Integer accountID);
    Integer getNextID();
}
