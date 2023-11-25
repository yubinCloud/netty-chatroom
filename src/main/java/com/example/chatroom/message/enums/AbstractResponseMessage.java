package com.example.chatroom.message.enums;

import com.example.chatroom.message.Message;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractResponseMessage extends Message {

    private boolean success;

    private String reason;  // 成功或失败时给的提示信息

}
