package com.ayf.areyoufull.entity;

public class Account {
    public static final String defaultAvatar = "./default.jpg";
    private Integer accountID;
    private String password;
    private String nickname;
    private String accountPhone;
    private String email;
    private String avatar;

    public Account() {}

    public Account(Integer accountID, String password, String nickname, String accountPhone, String email, String avatar) {
        this.accountID = accountID;
        this.password = password;
        this.nickname = nickname;
        this.accountPhone = accountPhone;
        this.email = email;
        this.avatar = avatar;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", accountPhone='" + accountPhone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
