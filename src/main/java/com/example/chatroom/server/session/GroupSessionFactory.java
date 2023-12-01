package com.example.chatroom.server.session;

public abstract class GroupSessionFactory {

    private static final GroupSession groupSession = new GroupSessionMemoryImpl();

    public static GroupSession get() {
        return groupSession;
    }

}
