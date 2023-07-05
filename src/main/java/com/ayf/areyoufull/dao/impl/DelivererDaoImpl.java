package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.DelivererDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.mapper.AccountMapper;
import com.ayf.areyoufull.mapper.DelivererMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DelivererDaoImpl implements DelivererDao {
    private final DelivererMapper delivererMapper;
    private final AccountMapper accountMapper;

    @Autowired
    public DelivererDaoImpl(DelivererMapper delivererMapper, AccountMapper accountMapper) {
        this.delivererMapper = delivererMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public Deliverer findDelivererByID(Integer id) {
        Deliverer deliverer = delivererMapper.findByID(id);
        Account account = accountMapper.findByID(id);
        deliverer.setAccount(account);
        return deliverer;
    }

    @Override
    public void newDeliverer(Deliverer deliverer) {
        delivererMapper.newDeliverer(deliverer);
        accountMapper.newAccount(deliverer.getAccount());
    }
}
