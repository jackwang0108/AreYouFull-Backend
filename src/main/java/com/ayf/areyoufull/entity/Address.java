package com.ayf.areyoufull.entity;

public class Address {
    private Integer accountID;
    private String address;
    private String phone;

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "accountID=" + accountID +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
