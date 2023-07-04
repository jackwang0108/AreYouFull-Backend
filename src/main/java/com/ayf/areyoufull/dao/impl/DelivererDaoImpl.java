package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.DelivererDao;
import com.ayf.areyoufull.mapper.DelivererMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DelivererDaoImpl implements DelivererDao {
    private final DelivererMapper delivererMapper;

    @Autowired
    public DelivererDaoImpl(DelivererMapper delivererMapper) {
        this.delivererMapper = delivererMapper;
    }
}
