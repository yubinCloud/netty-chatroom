package com.example.chatroom.command.handler;

public interface CommandHandler {

    void handle(String username, String[] args);
}
