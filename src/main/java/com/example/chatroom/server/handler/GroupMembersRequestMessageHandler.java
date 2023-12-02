package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.GroupMembersRequestMessage;
import com.example.chatroom.message.enums.GroupMembersResponseMessage;
import com.example.chatroom.server.session.GroupSession;
import com.example.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupMembersRequestMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMembersRequestMessage message) throws Exception {
        String groupName = message.getGroupName();
        GroupSession session = GroupSessionFactory.get();
        var members = session.getMembers(groupName);
        ctx.writeAndFlush(new GroupMembersResponseMessage(groupName, members));
    }
}
