package com.example.chatroom.server.service;

public interface UserService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
