package com.soul.pojo;

/**
 * Created by Administrator on 2019/3/28.
 */
public class Message {

    private int status;

    private String massage;

    public Message() {
    }

    public Message(int status, String massage) {
        this.status = status;
        this.massage = massage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
