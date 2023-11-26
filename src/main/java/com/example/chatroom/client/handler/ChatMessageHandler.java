package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.ChatMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ChatMessageHandler extends SimpleChannelInboundHandler<ChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage chatMessage) throws Exception {
        System.out.println("$ 收到来自'" + chatMessage.getFrom() + "'的消息：" + chatMessage.getContent());
    }
}
