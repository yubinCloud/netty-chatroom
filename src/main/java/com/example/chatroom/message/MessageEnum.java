package com.example.chatroom.message;


public enum MessageEnum {

    LOGIN_REQUEST_MESSAGE(0),
    LOGIN_RESPONSE_MESSAGE(1),
    CHAT_REQUEST_MESSAGE(2),
    CHAT_RESPONSE_MESSAGE(3),
    CHAT_MESSAGE(4),
    GROUP_CREATE_REQUEST_MESSAGE(5),
    GROUP_CREATE_RESPONSE_MESSAGE(6);

    private final int typeValue;

    MessageEnum(int typeValue) {
        this.typeValue = typeValue;
    }

    public int getTypeValue() {
        return typeValue;
    }

}
