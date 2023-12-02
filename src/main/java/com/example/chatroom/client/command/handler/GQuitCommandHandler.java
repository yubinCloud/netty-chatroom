package com.example.chatroom.client.command.handler;

import com.example.chatroom.message.enums.GroupQuitRequestMessage;
import io.netty.channel.ChannelHandlerContext;

public class GQuitCommandHandler implements CommandHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String username, String[] args) {
        String groupName = args[1];
        ctx.writeAndFlush(new GroupQuitRequestMessage(username, groupName));
    }
}
