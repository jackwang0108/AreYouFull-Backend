package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.AddressDao;
import com.ayf.areyoufull.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl implements AddressDao {
    private final AddressMapper addressMapper;

    @Autowired
    public AddressDaoImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }
}
