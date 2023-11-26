package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.ChatMessage;
import com.example.chatroom.message.enums.ChatRequestMessage;
import com.example.chatroom.message.enums.ChatResponseMessage;
import com.example.chatroom.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage message) {
        String to = message.getTo();
        Channel channel = SessionFactory.get().getChannel(to);
        if (Objects.nonNull(channel)) {  // 表名这个 to 用户在线
            channel.writeAndFlush(new ChatMessage(message.getFrom(), message.getContent()));
        } else {
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或不在线"));
        }
    }
}
