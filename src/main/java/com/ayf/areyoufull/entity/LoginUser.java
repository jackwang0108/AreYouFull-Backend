package com.ayf.areyoufull.entity;

public class LoginUser {
    private Integer accountID;
    private String password;
    private String verificationCode;
    private String verificationKey;

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "accountID=" + accountID +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", verificationKey='" + verificationKey + '\'' +
                '}';
    }
}

