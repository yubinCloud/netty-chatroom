package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.GroupCreateResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class GroupCreateResponseMessageHandler extends SimpleChannelInboundHandler<GroupCreateResponseMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupCreateResponseMessage message) throws Exception {
        System.out.println("$ 群聊提示：" + message.getReason());
    }
}
