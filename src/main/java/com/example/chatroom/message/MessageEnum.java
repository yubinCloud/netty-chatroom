package com.example.chatroom.message;


public enum MessageEnum {

    LOGIN_REQUEST_MESSAGE(0),
    LOGIN_RESPONSE_MESSAGE(1),
    CHAT_REQUEST_MESSAGE(2),
    CHAT_RESPONSE_MESSAGE(3),
    CHAT_MESSAGE(4),
    GROUP_CREATE_REQUEST_MESSAGE(5),
    GROUP_CREATE_RESPONSE_MESSAGE(6),
    GROUP_MEMBERS_REQUEST_MESSAGE(7),
    GROUP_MEMBERS_RESPONSE_MESSAGE(8),
    GROUP_JOIN_REQUEST_MESSAGE(9),
    GROUP_JOIN_RESPONSE_MESSAGE(10),
    GROUP_QUIT_REQUEST_MESSAGE(11),
    GROUP_QUIT_RESPONSE_MESSAGE(12),
    GROUP_CHAT_REQUEST_MESSAGE(13),
    GROUP_CHAT_RESPONSE_MESSAGE(14);

    private final int typeValue;

    MessageEnum(int typeValue) {
        this.typeValue = typeValue;
    }

    public int getTypeValue() {
        return typeValue;
    }

}
