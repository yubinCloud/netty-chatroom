package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoginRequestMessage extends Message {

    private String username;

    private String password;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.LOGIN_REQUEST_MESSAGE;
    }
}
