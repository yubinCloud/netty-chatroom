package com.example.chatroom.message;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Message implements Serializable {

    private int sequentId;

    private int messageType;

    public abstract MessageEnum getMessageType();
}
