package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Account;

public interface AccountDao {
    // 准备弃用
    Account findByID(Integer id);
    // 准备弃用
    void newAccount(Account account);
    Integer getNextID();
}
