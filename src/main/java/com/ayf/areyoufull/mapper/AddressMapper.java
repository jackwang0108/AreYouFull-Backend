package com.ayf.areyoufull.mapper;

import com.ayf.areyoufull.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddressMapper {
    Address findByAccountID(@Param("accountID") Integer accountID);
    Integer newAddress(@Param("address") Address address);
}
