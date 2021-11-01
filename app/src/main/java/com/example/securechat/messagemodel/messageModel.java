package com.example.securechat.messagemodel;

import com.example.securechat.Cryptography.AES;

public class messageModel {
    String uid,message;
    Long timeStamp;

    public messageModel(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    public messageModel(String uid, String message, Long timeStamp) {
        this.uid = uid;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public messageModel() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        AES aes=new AES();
        this.message=aes.decrypt(message);

    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
