package com.example.chatroom.server.service;

public abstract class UserServiceFactory {

    private static final UserService userService = new UserServiceMemoryImpl();

    public static UserService get() {
        return userService;
    }
}
