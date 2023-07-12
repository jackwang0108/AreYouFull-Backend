package com.ayf.areyoufull.entity;

import java.util.List;
import java.util.Random;

public class Shop {
    private Integer shopID;
    private Integer merchantID;
    private Account account;
    private List<Address> address;
    private String shopName;
    private String shopImg = "./defaultShop.jpg";
    private String shopIntro = "这个商家很懒，什么都没有留下";
    private Integer sales = 200 + new Random().nextInt(50);
    private Double rating = 4.0 + new Random().nextDouble(1.0);

    public Shop() {}

    public Shop(Integer shopID, Integer merchantID, Account account, List<Address> address, String shopName, String shopImg, String shopIntro, Integer sales, Double rating) {
        this.shopID = shopID;
        this.merchantID = merchantID;
        this.account = account;
        this.address = address;
        this.shopName = shopName;
        this.shopImg = shopImg;
        this.shopIntro = shopIntro;
        this.sales = sales;
        this.rating = rating;
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

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopID=" + shopID +
                ", merchantID=" + merchantID +
                ", account=" + account +
                ", address=" + address.get(0) +
                ", shopName='" + shopName + '\'' +
                ", shopImg='" + shopImg + '\'' +
                ", shopIntro='" + shopIntro + '\'' +
                ", sales='" + sales + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
