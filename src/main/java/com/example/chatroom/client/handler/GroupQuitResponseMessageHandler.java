package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.GroupQuitResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupQuitResponseMessageHandler extends SimpleChannelInboundHandler<GroupQuitResponseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitResponseMessage message) throws Exception {
        System.out.println("$ " + message.getReason());
    }
}
