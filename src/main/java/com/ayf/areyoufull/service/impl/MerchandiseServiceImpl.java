package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.MerchandiseDao;
import com.ayf.areyoufull.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    private final MerchandiseDao merchandiseDao;

    @Autowired
    public MerchandiseServiceImpl(MerchandiseDao merchandiseDao) {
        this.merchandiseDao = merchandiseDao;
    }
}
