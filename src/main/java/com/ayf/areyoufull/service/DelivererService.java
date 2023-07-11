package com.ayf.areyoufull.service;

import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.Order;

import java.util.List;

public interface DelivererService {
    Deliverer getDelivererByDelivererID(Integer id);
    void newDeliverer(Deliverer deliverer);
    void modifyDelivererInfo(Deliverer deliverer);
    void terminateByDeliverer(Deliverer deliverer);
    List<Order> queryOrderByStatus(Order order);
    public List<Order> querySelfOrderByStatus(Order order);
    void updateOrder(Order order);
}
