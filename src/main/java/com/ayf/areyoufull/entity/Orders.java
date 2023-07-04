package com.ayf.areyoufull.entity;


import java.util.Date;

public class Orders {
    private Integer orderID;
    private Integer userID;
    private Integer shopID;
    private Integer delivererID;
    private OrderDetail orderDetail;
    private Byte status;
    private Date createTime;
    private Date payTime;
    private Date merchantAssumeTime;
    private Date merchantFinishTime;
    private Date delivererGetTime;
    private Date finishTime;

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

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
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

    public Date getMerchantAssumeTime() {
        return merchantAssumeTime;
    }

    public void setMerchantAssumeTime(Date merchantAssumeTime) {
        this.merchantAssumeTime = merchantAssumeTime;
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
        return "Orders{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", shopID=" + shopID +
                ", delivererID=" + delivererID +
                ", orderDetail=" + orderDetail +
                ", status=" + status +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", merchantAssumeTime=" + merchantAssumeTime +
                ", merchantFinishTime=" + merchantFinishTime +
                ", delivererGetTime=" + delivererGetTime +
                ", finishTime=" + finishTime +
                '}';
    }
}
