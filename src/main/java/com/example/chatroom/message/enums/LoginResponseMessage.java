package com.example.chatroom.message.enums;

import com.example.chatroom.message.MessageEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.LOGIN_RESPONSE_MESSAGE;
    }
}
