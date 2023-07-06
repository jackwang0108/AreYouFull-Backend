package com.ayf.areyoufull.service;

import com.ayf.areyoufull.entity.Deliverer;

public interface DelivererService {
    Deliverer getDelivererByDelivererID(Integer id);
    void newDeliverer(Deliverer deliverer);
    void modifyDelivererInfo(Deliverer deliverer);
    void terminateByDeliverer(Deliverer deliverer);
}
