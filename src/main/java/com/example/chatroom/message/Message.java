package com.example.chatroom.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public abstract class Message implements Serializable {

    private int sequenceId;

    private int messageType;

    public abstract MessageEnum getMessageType();
}
