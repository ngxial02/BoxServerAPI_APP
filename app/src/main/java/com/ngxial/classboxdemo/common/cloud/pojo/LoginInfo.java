package com.ngxial.classboxdemo.common.cloud.pojo;

public class LoginInfo {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ClassPojo [user = " + user + "]";
    }
}
