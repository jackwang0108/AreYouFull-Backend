package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Deliverer;

public interface DelivererDao {
    Deliverer findDelivererByID(Integer id);
    void newDeliverer(Deliverer deliverer);
}
