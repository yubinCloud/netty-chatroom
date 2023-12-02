package com.example.chatroom.message.enums;

import com.example.chatroom.message.MessageEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HeartbeatResponseMessage extends AbstractResponseMessage {
    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.HEARTBEAT_RESPONSE_MESSAGE;
    }
}
