package com.ayf.areyoufull.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private Integer userID;
    private Account account;
    private List<Address> addresses;

    public User() {}

    @JsonCreator
    public User(@JsonProperty("userID") Integer userID,
                @JsonProperty("account") Account account,
                @JsonProperty("addresses") List<Address> addresses) {
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", account=" + account +
                ", addresses=" + addresses +
                '}';
    }
}
