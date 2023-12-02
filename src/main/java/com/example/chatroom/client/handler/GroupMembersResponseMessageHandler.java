package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.GroupMembersResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupMembersResponseMessageHandler extends SimpleChannelInboundHandler<GroupMembersResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMembersResponseMessage message) throws Exception {
        System.out.println("$ 群聊 " + message.getGroupName() + " 的成员：" + message.getMembers());
    }
}
