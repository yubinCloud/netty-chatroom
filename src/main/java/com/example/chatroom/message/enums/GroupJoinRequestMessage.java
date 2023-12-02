package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupJoinRequestMessage extends Message {

    private String username;

    private String groupName;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.GROUP_JOIN_REQUEST_MESSAGE;
    }
}
