package com.example.chatroom.client.command.handler;

import com.example.chatroom.message.enums.ChatRequestMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * 处理 send command
 */
public class SendCommandHandler implements CommandHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String username, String[] args) {
        String to = args[1];
        String content = args[2];
        ctx.writeAndFlush(new ChatRequestMessage(content, to, username));
    }
}
