package com.example.chatroom.client.command.handler;

import com.example.chatroom.message.enums.GroupJoinRequestMessage;
import io.netty.channel.ChannelHandlerContext;

public class GJoinCommandHandler implements CommandHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String username, String[] args) {
        String groupName = args[1];
        ctx.writeAndFlush(new GroupJoinRequestMessage(username, groupName));
    }
}
