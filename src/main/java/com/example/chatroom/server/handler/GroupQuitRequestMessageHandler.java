package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.GroupQuitRequestMessage;
import com.example.chatroom.message.enums.GroupQuitResponseMessage;
import com.example.chatroom.server.session.GroupSession;
import com.example.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {

    private static GroupSession groupSession = GroupSessionFactory.get();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage message) throws Exception {
        var group = groupSession.removeMember(message.getGroupName(), message.getUsername());
        if (Objects.isNull(group)) {
            ctx.writeAndFlush(new GroupQuitResponseMessage(false, "群组不存在或成员不存在"));
        } else {
            ctx.writeAndFlush(new GroupQuitResponseMessage(true, "移除成员成功"));
        }
    }
}
