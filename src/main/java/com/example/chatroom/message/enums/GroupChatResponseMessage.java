package com.example.chatroom.message.enums;

import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupChatResponseMessage extends AbstractResponseMessage {

    private String from;

    private String groupName;

    private String content;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.GROUP_CHAT_RESPONSE_MESSAGE;
    }
}
