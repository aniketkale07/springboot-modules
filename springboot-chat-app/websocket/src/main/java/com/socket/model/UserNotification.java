package com.socket.model;

public class UserNotification {
    private String username;

    public UserNotification() {}

    public UserNotification(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
