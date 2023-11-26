package com.example.chatroom.message.enums;

import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChatResponseMessage extends AbstractResponseMessage {

    private String from;

    private String content;

    public ChatResponseMessage(boolean isSuccess, String reason) {
        super(isSuccess, reason);
    }

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.CHAT_RESPONSE_MESSAGE;
    }
}
