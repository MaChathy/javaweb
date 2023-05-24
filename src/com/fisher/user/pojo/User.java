package com.fisher.user.pojo;

/**
 * 用户类
 * @author fisher
 * @version 1.0.1 2023/5/24 - 10:38
 */
public class User {
    private int id;
    private String account;
    private String PASSWORD;
    private String nickname;

    public User() {}

    public User(int id, String account, String password, String nickname) {
        this.id = id;
        this.account = account;
        this.PASSWORD = password;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.PASSWORD = password;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + PASSWORD + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
