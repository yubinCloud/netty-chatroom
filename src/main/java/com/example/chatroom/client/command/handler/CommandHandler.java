package com.example.chatroom.client.command.handler;

import io.netty.channel.ChannelHandlerContext;

public interface CommandHandler {

    void handle(ChannelHandlerContext ctx, String username, String[] args);
}
