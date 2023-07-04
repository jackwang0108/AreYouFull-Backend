package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Account;

public interface AccountDao {
    Account findByID(Integer id);
}
