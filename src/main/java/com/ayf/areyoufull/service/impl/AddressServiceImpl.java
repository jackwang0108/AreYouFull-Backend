package com.ayf.areyoufull.service.impl;

import com.ayf.areyoufull.dao.AddressDao;
import com.ayf.areyoufull.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
}
