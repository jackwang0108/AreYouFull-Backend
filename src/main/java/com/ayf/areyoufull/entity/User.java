package com.ayf.areyoufull.entity;

import java.util.Arrays;

public class User {
    private Integer userID;
    private Account account;
    private Address[] addresses;

    public User() {}

    public User(Integer userID, Account account, Address[] addresses) {
        this.userID = userID;
        this.account = account;
        this.addresses = addresses;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address[] getAddresses() {
        return addresses;
    }

    public void setAddresses(Address[] addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", account=" + account +
                ", addresses=" + Arrays.toString(addresses) +
                '}';
    }
}
