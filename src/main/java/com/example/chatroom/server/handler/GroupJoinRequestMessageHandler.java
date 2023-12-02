package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.GroupJoinRequestMessage;
import com.example.chatroom.message.enums.GroupJoinResponseMessage;
import com.example.chatroom.server.session.GroupSession;
import com.example.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage message) throws Exception {
        String username = message.getUsername();
        String groupName = message.getGroupName();
        GroupSession groupSession = GroupSessionFactory.get();
        var group = groupSession.joinMember(groupName, username);
        if (Objects.isNull(group)) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(false, "群组 " + groupName + " 不存在"));
        } else {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, "加入群组成功"));
        }
    }
}
