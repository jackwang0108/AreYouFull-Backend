package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.AccountDao;
import com.ayf.areyoufull.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
