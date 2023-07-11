package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.DelivererDao;
import com.ayf.areyoufull.dao.OrderDao;
import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.Order;
import com.ayf.areyoufull.service.DelivererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DelivererServiceImpl implements DelivererService {
    private final DelivererDao delivererDao;
    private final OrderDao orderDao;

    @Autowired
    public DelivererServiceImpl(DelivererDao delivererDao, OrderDao orderDao) {
        this.delivererDao = delivererDao;
        this.orderDao = orderDao;
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

    @Override
    public List<Order> queryOrderByStatus(Order order) {
        return orderDao.findOrderByStatus(order);
    }

    @Override
    public List<Order> querySelfOrderByStatus(Order order) {
        return orderDao.findDelivererOrderByStatus(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }
}
