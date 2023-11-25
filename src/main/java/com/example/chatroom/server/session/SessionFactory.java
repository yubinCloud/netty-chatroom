package com.example.chatroom.server.session;

public abstract class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    public static Session get() {
        return session;
    }
}
