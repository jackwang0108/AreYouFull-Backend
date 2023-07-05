package com.ayf.areyoufull.entity;

public class Deliverer {
    private Integer delivererID;
    private Account account;

    public Deliverer() {}

    public Deliverer(Integer delivererID, Account account) {
        this.delivererID = delivererID;
        this.account = account;
    }

    public Integer getDelivererID() {
        return delivererID;
    }

    public void setDelivererID(Integer delivererID) {
        this.delivererID = delivererID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Deliverer{" +
                "delivererID=" + delivererID +
                ", account=" + account +
                '}';
    }
}
