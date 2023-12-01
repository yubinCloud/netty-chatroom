package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupCreateRequestMessage extends Message {

    private String groupName;

    private Set<String> members;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.GROUP_CREATE_REQUEST_MESSAGE;
    }
}
