package com.ayf.areyoufull.entity;

public class CurrentAccount {
    private Integer accountID;
    private String nickname;

    public CurrentAccount() {}

    public CurrentAccount(Integer accountID, String nickname) {
        this.accountID = accountID;
        this.nickname = nickname;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
