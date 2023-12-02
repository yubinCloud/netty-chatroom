package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.GroupChatRequestMessage;
import com.example.chatroom.message.enums.GroupChatResponseMessage;
import com.example.chatroom.server.session.GroupSession;
import com.example.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {

    private final static GroupSession groupSession = GroupSessionFactory.get();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage message) throws Exception {
        var channels = groupSession.getMembersChannel(message.getGroupName());
        var chatMessage = new GroupChatResponseMessage(message.getFrom(), message.getGroupName(), message.getContent());
        channels.forEach(channel -> channel.writeAndFlush(chatMessage));
    }
}
