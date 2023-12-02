package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HeartbeatRequestMessage extends Message {
    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.HEARTBEAT_REQUEST_MESSAGE;
    }
}
