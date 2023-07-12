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
    public Deliverer findDelivererByDelivererID(Integer id) {
        Deliverer deliverer = delivererMapper.findByDelivererID(id);
        if (deliverer == null) return null;
        Account account = accountMapper.findByAccountID(id);
        deliverer.setAccount(account);
        return deliverer;
    }

    @Override
    public void newDeliverer(Deliverer deliverer) {
        accountMapper.newAccount(deliverer.getAccount());
        delivererMapper.newDeliverer(deliverer);
    }

    @Override
    public void modifyDelivererInfo(Deliverer deliverer) {
        accountMapper.updateAccount(deliverer.getAccount());
        delivererMapper.updateDeliverer(deliverer);
    }

    @Override
    public void terminateByDeliverer(Deliverer deliverer) {
        delivererMapper.deleteByDelivererID(deliverer.getDelivererID());
        accountMapper.deleteByAccountID(deliverer.getDelivererID());
    }
}
