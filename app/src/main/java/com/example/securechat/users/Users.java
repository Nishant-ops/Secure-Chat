package com.example.securechat.users;

public class Users {
    private String Username;
    private String Uid;
    private String last_message;

    public Users(String username,String uid,String last_message)
    {
        Username=username;
        Uid=uid;
        this.last_message=last_message;
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
}
