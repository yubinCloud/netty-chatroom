package com.example.chatroom.server.handler.factory;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import com.example.chatroom.server.handler.*;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ServerHandlerFactory {

    private static final Map<MessageEnum, SimpleChannelInboundHandler<? extends Message>> MESSAGE_HANDLER_MAP = new ConcurrentHashMap<>();

    static {
        MESSAGE_HANDLER_MAP.put(MessageEnum.LOGIN_REQUEST_MESSAGE, new LoginRequestMessageHandler());
        MESSAGE_HANDLER_MAP.put(MessageEnum.CHAT_REQUEST_MESSAGE, new ChatRequestMessageHandler());
        MESSAGE_HANDLER_MAP.put(MessageEnum.GROUP_CREATE_REQUEST_MESSAGE, new GroupCreateRequestMessageHandler());
        MESSAGE_HANDLER_MAP.put(MessageEnum.GROUP_MEMBERS_REQUEST_MESSAGE, new GroupMembersRequestMessageHandler());
        MESSAGE_HANDLER_MAP.put(MessageEnum.GROUP_JOIN_REQUEST_MESSAGE, new GroupJoinRequestMessageHandler());
        MESSAGE_HANDLER_MAP.put(MessageEnum.GROUP_QUIT_REQUEST_MESSAGE, new GroupQuitRequestMessageHandler());
    }

    public static SimpleChannelInboundHandler<? extends Message> createMessageHandler(MessageEnum messageEnum) {
        return MESSAGE_HANDLER_MAP.get(messageEnum);
    }
}
