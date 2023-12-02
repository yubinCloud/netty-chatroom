package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.GroupJoinResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupJoinResponseMessageHandler extends SimpleChannelInboundHandler<GroupJoinResponseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinResponseMessage message) throws Exception {
        System.out.println("$ " + message.getReason());
    }
}


