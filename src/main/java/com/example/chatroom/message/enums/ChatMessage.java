package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatMessage extends Message {

    private String from;

    private String content;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.CHAT_MESSAGE;
    }
}
