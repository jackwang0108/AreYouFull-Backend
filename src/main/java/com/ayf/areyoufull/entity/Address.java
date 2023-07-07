package com.ayf.areyoufull.entity;

public class Address {
    private Integer addressID;
    private Integer accountID;
    private String address;
    private String phone;

    public Address() {}

    public Address(Integer addressID, Integer accountID, String address, String phone) {
        this.addressID = addressID;
        this.accountID = accountID;
        this.address = address;
        this.phone = phone;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

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
        return "Address{" +
                "addressID=" + addressID +
                ", accountID=" + accountID +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
