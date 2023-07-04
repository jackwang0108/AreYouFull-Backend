package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.MerchandiseDao;
import com.ayf.areyoufull.mapper.MerchandiseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchandiseDaoImpl implements MerchandiseDao {
    private final MerchandiseMapper merchandiseMapper;

    @Autowired
    public MerchandiseDaoImpl(MerchandiseMapper merchandiseMapper) {
        this.merchandiseMapper = merchandiseMapper;
    }
}
