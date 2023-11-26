package com.example.chatroom.client.command;

import com.example.chatroom.client.command.handler.*;

public enum CommandEnum {

    SEND("send", new SendCommandHandler()),
    GSEND("gsend", new GSendCommandHandler()),
    GCREATE("gcreate", new GCreateCommandHandler()),
    GMEMBERS("gmembers", new GMembersCommandHandler()),
    GJOIN("gjoin", new GJoinCommandHandler()),
    GQUIT("gquit", new GQuitCommandHandler()),
    QUIT("quit", null);

    private final String value;

    private final CommandHandler handler;

    CommandEnum(String value, CommandHandler handler) {
        this.value = value.toUpperCase();
        this.handler = handler;
    }

    public String getValue() {
        return value;
    }

    public CommandHandler getHandler() {
        return handler;
    }
}
