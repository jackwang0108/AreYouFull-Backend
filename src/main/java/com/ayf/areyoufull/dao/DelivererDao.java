package com.ayf.areyoufull.dao;

import com.ayf.areyoufull.entity.Deliverer;
import com.ayf.areyoufull.entity.User;

public interface DelivererDao {
    Deliverer findDelivererByDelivererID(Integer id);
    void newDeliverer(Deliverer deliverer);
    void modifyDelivererInfo(Deliverer deliverer);
    void terminateByDeliverer(Deliverer deliverer);
}
