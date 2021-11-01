package com.example.securechat.users;

public class Users {
    private String Username;
    private String Uid;
    private String last_message;
    private String password;
    private String email;

    public Users(String username, String uid, String last_message, String password, String email)
    {
        this.Username=username;
        this.Uid=uid;
        this.last_message=last_message;
        this.password = password;
        this.email = email;
    }

    public Users(String username, String password, String email) {
        Username = username;
        this.password = password;
        this.email = email;
    }

    public  Users()
    {}

    public String getUsername() {
        return Username;
    }

    public String getUid() {
        return Uid;
    }

    public String getLast_message() {
        return last_message;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
