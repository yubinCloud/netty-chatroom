package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupChatRequestMessage extends Message {

    private String content;

    private String groupName;

    private String from;


    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.GROUP_CHAT_REQUEST_MESSAGE;
    }
}
