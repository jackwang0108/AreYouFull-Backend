package com.ayf.areyoufull.entity;

public class RegisterAccount {
    private int type;
    private String password;
    private String nickname;
    private String accountPhone;
    private String email;

    public RegisterAccount(int type, String password, String accountPhone) {
        this.type = type;
        this.password = password;
        this.accountPhone = accountPhone;
    }

    public RegisterAccount(int type, String password, String nickname, String accountPhone, String email) {
        this.type = type;
        this.password = password;
        this.nickname = nickname;
        this.accountPhone = accountPhone;
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
