package com.ayf.areyoufull.entity;

public class OrderDetail {
    private Integer orderID;
    private Integer merchandiseID;
    private Integer orderNum;

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getMerchandiseID() {
        return merchandiseID;
    }

    public void setMerchandiseID(Integer merchandiseID) {
        this.merchandiseID = merchandiseID;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderID=" + orderID +
                ", merchandiseID=" + merchandiseID +
                ", orderNum=" + orderNum +
                '}';
    }
}
