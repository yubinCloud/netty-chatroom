package com.example.chatroom.message;


public enum MessageEnum {

    LOGIN_REQUEST_MESSAGE(0),
    LOGIN_RESPONSE_MESSAGE(1);

    private final int typeValue;

    MessageEnum(int typeValue) {
        this.typeValue = typeValue;
    }

    public int getTypeValue() {
        return typeValue;
    }

}
