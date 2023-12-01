package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupCreateResponseMessage extends AbstractResponseMessage {

    public GroupCreateResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.GROUP_CREATE_RESPONSE_MESSAGE;
    }
}
