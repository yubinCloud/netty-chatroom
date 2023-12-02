package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.GroupChatResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupChatResponseMessageHandler extends SimpleChannelInboundHandler<GroupChatResponseMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatResponseMessage message) throws Exception {
        System.out.printf("* 群消息【%s】%s：%s\n", message.getGroupName(), message.getFrom(), message.getContent());
    }
}
