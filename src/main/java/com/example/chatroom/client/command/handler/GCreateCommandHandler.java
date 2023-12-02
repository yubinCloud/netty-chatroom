package com.example.chatroom.client.command.handler;

import com.example.chatroom.message.enums.GroupCreateRequestMessage;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GCreateCommandHandler implements CommandHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, String username, String[] args) {
        String groupName = args[1];
        Set<String> members = new HashSet<>(Arrays.asList(args).subList(2, args.length));
        members.add(username);
        GroupCreateRequestMessage message = new GroupCreateRequestMessage(groupName, members);
        ctx.writeAndFlush(message);
    }
}
