package com.example.chatroom.client.command.handler;

import com.example.chatroom.message.enums.GroupChatRequestMessage;
import io.netty.channel.ChannelHandlerContext;

public class GSendCommandHandler implements CommandHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String username, String[] args) {
        String groupName = args[1];
        String content = args[2];
        ctx.writeAndFlush(new GroupChatRequestMessage(content, groupName, username));
    }
}
