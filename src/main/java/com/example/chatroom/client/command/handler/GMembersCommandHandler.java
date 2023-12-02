package com.example.chatroom.client.command.handler;

import com.example.chatroom.message.enums.GroupMembersRequestMessage;
import io.netty.channel.ChannelHandlerContext;

public class GMembersCommandHandler implements CommandHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String username, String[] args) {
        String groupName = args[1];
        ctx.writeAndFlush(new GroupMembersRequestMessage(groupName));
    }
}
