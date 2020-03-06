package com.studentonline.homework.entities;

/**
 * @author 19892
 */
public class User {
    private String userId;
    private String psw;

    public User(String userId, String psw) {
        this.userId = userId;
        this.psw = psw;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
}
