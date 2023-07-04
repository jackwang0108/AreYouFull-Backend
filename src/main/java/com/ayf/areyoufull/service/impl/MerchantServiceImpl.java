package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.MerchantDao;
import com.ayf.areyoufull.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantDao merchantDao;

    @Autowired
    public MerchantServiceImpl(MerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }
}
