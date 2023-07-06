package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> findByAccountID(@Param("accountID") Integer accountID);
    Integer newAddress(@Param("address") Address address);
}
