package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.DelivererDao;
import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.service.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelivererServiceImpl implements DelivererService {
    private final DelivererDao delivererDao;

    @Autowired
    public DelivererServiceImpl(DelivererDao delivererDao) {
        this.delivererDao = delivererDao;
    }

    @Override
    public Deliverer getDelivererByDelivererID(Integer id) {
        return delivererDao.findDelivererByDelivererID(id);
    }

    @Override
    public void newDeliverer(Deliverer deliverer) {
        delivererDao.newDeliverer(deliverer);
    }

    @Override
    public void modifyDelivererInfo(Deliverer deliverer) {
        delivererDao.modifyDelivererInfo(deliverer);
    }

    @Override
    public void terminateByDeliverer(Deliverer deliverer) {
        delivererDao.terminateByDeliverer(deliverer);
    }
}
