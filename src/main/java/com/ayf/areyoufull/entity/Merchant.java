package com.ayf.areyoufull.entity;

public class Merchant {
    private Integer merchantID;
    private Account account;
    private Address address;

    public Integer getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(Integer merchantID) {
        this.merchantID = merchantID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantID=" + merchantID +
                ", account=" + account +
                ", address=" + address +
                '}';
    }
}
