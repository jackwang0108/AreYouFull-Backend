package com.ayf.areyoufull.service;

import com.ayf.areyoufull.entity.Deliverer;

public interface DelivererService {
    Deliverer getDelivererByID(Integer id);
    void newDeliverer(Deliverer deliverer);
}
