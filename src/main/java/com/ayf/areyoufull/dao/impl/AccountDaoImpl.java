package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.AccountDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountDaoImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findByID(Integer id) {
        return accountMapper.findByID(id);
    }

    @Override
    public Integer getNextID() {
        return accountMapper.getNextID();
    }
}
