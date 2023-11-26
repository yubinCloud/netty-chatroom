package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatRequestMessage extends Message {

    private String content;

    private String to;

    private String from;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.CHAT_REQUEST_MESSAGE;
    }
}
