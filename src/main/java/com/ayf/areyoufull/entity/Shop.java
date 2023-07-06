package com.ayf.areyoufull.entity;

public class Shop {
    private Integer shopID;
    private Integer merchantID;
    private Account account;
    private Address address;
    private String shopName;
    private String shopImg;
    private String shopIntro;

    public Shop() {}

    public Shop(Integer shopID, Integer merchantID, Account account, Address address, String shopName, String shopImg, String shopIntro) {
        this.shopID = shopID;
        this.merchantID = merchantID;
        this.account = account;
        this.address = address;
        this.shopName = shopName;
        this.shopImg = shopImg;
        this.shopIntro = shopIntro;
    }

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopIntro() {
        return shopIntro;
    }

    public void setShopIntro(String shopIntro) {
        this.shopIntro = shopIntro;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopID=" + shopID +
                ", merchantID=" + merchantID +
                ", shopName='" + shopName + '\'' +
                ", shopImg='" + shopImg + '\'' +
                ", shopIntro='" + shopIntro + '\'' +
                '}';
    }
}
