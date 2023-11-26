package com.example.chatroom.server.handler.factory;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.MessageEnum;
import com.example.chatroom.server.handler.ChatRequestMessageHandler;
import com.example.chatroom.server.handler.LoginRequestMessageHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ServerHandlerFactory {

    private static final Map<MessageEnum, SimpleChannelInboundHandler<? extends Message>> MESSAGE_HANDLER_MAP = new ConcurrentHashMap<>();

    static {
        MESSAGE_HANDLER_MAP.put(MessageEnum.LOGIN_REQUEST_MESSAGE, new LoginRequestMessageHandler());
        MESSAGE_HANDLER_MAP.put(MessageEnum.CHAT_REQUEST_MESSAGE, new ChatRequestMessageHandler());
    }

    public static SimpleChannelInboundHandler<? extends Message> createMessageHandler(MessageEnum messageEnum) {
        return MESSAGE_HANDLER_MAP.get(messageEnum);
    }
}
