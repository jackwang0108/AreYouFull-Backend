package com.ayf.areyoufull.dao.impl;

import com.ayf.areyoufull.dao.UserDao;
import com.ayf.areyoufull.entity.Account;
import com.ayf.areyoufull.entity.Address;
import com.ayf.areyoufull.entity.User;
import com.ayf.areyoufull.mapper.AccountMapper;
import com.ayf.areyoufull.mapper.AddressMapper;
import com.ayf.areyoufull.mapper.OrderMapper;
import com.ayf.areyoufull.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    private final AddressMapper addressMapper;
    private final OrderMapper orderMapper;

    @Autowired
    public UserDaoImpl(UserMapper userMapper, AccountMapper accountMapper, AddressMapper addressMapper, OrderMapper orderMapper) {
        this.userMapper = userMapper;
        this.accountMapper = accountMapper;
        this.addressMapper = addressMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public User findUserByUserID(Integer userID) {
        User user = userMapper.findByID(userID);
        Account account = accountMapper.findByAccountID(userID);
        user.setAccount(account);
        List<Address> addresses = addressMapper.findByAccountID(userID);
        user.setAddresses(addresses);
        return user;
    }

    @Override
    public void newUser(User user) {
        accountMapper.newAccount(user.getAccount());
        userMapper.newUser(user);
    }

    @Override
    public void modifyUserInfo(User user) {
        accountMapper.updateAccount(user.getAccount());
        List<Address> addresses = addressMapper.findByAccountID(user.getUserID());
        if (addresses.isEmpty())
            user.getAddresses().forEach(addressMapper::newAddress);
        else
            user.getAddresses().forEach(address -> addresses.forEach(addr -> {
                if (!address.getAddress().equals(addr.getAddress())
                || !address.getPhone().equals(addr.getPhone()))
                    addressMapper.newAddress(address);
                else
                    addressMapper.updateByAddress(address);
            }));
        userMapper.updateUser(user);
    }

    @Override
    public void terminateByUser(User user) {
        userMapper.deleteByUserID(user.getUserID());
        addressMapper.deleteByAccountID(user.getUserID());
        accountMapper.deleteByAccountID(user.getUserID());
    }
}
