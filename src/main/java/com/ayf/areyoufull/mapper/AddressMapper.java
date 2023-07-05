package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    Address findByID(Integer id);
    void newAddress(Address address);
}
