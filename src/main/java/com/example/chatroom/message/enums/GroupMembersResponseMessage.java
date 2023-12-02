package com.example.chatroom.message.enums;

import com.example.chatroom.message.MessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
public class GroupMembersResponseMessage extends AbstractResponseMessage {

    private String groupName;

    private Set<String> members;

    @Override
    public MessageEnum getMessageType() {
        return MessageEnum.GROUP_MEMBERS_RESPONSE_MESSAGE;
    }
}
