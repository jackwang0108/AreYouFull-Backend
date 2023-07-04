package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.MerchantDao;
import com.ayf.areyoufull.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDaoImpl implements MerchantDao {
    private final MerchantMapper merchantMapper;

    @Autowired
    public MerchantDaoImpl(MerchantMapper merchantMapper) {
        this.merchantMapper = merchantMapper;
    }
}
