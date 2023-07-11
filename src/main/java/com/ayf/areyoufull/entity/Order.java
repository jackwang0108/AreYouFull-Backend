package com.ayf.areyoufull.entity;


import com.ayf.areyoufull.dao.IDGenerator;

import java.util.Date;
import java.util.List;

public class Order {
    public static Order createOrder(Integer userID, Integer shopID, List<OrderDetail> orderDetail){
        return new Order(IDGenerator.getNextOrderID(), userID, shopID, null, orderDetail, (byte)0, new Date(), null, null, null, null, null);
    }
    public static Byte ORDER_CREATED = 0;
    public static Byte ORDER_PAYED = 1;
    public static Byte ORDER_MERCHANT_ASSURED = 2;
    public static Byte ORDER_MERCHANT_FINISHED = 3;
    public static Byte ORDER_DELIVERER_GOT = 4;
    public static Byte ORDER_FINISHED = 5;
    public static Byte ORDER_CANCELLED = 6;

    private Integer orderID;
    private Integer userID;
    private Integer shopID;
    private Integer delivererID;
    private List<OrderDetail> orderDetail;
    private Byte status;
    private Date createTime;
    private Date payTime;
    private Date merchantAssureTime;
    private Date merchantFinishTime;
    private Date delivererGetTime;
    private Date finishTime;

    public Order() {}

    public Order(Integer orderID, Integer userID, Integer shopID, Integer delivererID, List<OrderDetail> orderDetail, Byte status, Date createTime, Date payTime, Date merchantAssureTime, Date merchantFinishTime, Date delivererGetTime, Date finishTime) {
        this.orderID = orderID;
        this.userID = userID;
        this.shopID = shopID;
        this.delivererID = delivererID;
        this.orderDetail = orderDetail;
        this.status = status;
        this.createTime = createTime;
        this.payTime = payTime;
        this.merchantAssureTime = merchantAssureTime;
        this.merchantFinishTime = merchantFinishTime;
        this.delivererGetTime = delivererGetTime;
        this.finishTime = finishTime;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getShopID() {
        return shopID;
    }

    public void setShopID(Integer shopID) {
        this.shopID = shopID;
    }

    public Integer getDelivererID() {
        return delivererID;
    }

    public void setDelivererID(Integer delivererID) {
        this.delivererID = delivererID;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getMerchantAssureTime() {
        return merchantAssureTime;
    }

    public void setMerchantAssureTime(Date merchantAssureTime) {
        this.merchantAssureTime = merchantAssureTime;
    }

    public Date getMerchantFinishTime() {
        return merchantFinishTime;
    }

    public void setMerchantFinishTime(Date merchantFinishTime) {
        this.merchantFinishTime = merchantFinishTime;
    }

    public Date getDelivererGetTime() {
        return delivererGetTime;
    }

    public void setDelivererGetTime(Date delivererGetTime) {
        this.delivererGetTime = delivererGetTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", shopID=" + shopID +
                ", delivererID=" + delivererID +
                ", orderDetail=" + orderDetail +
                ", status=" + status +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", merchantAssureTime=" + merchantAssureTime +
                ", merchantFinishTime=" + merchantFinishTime +
                ", delivererGetTime=" + delivererGetTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
