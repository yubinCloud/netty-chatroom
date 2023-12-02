package com.example.chatroom.client.handler;

import com.example.chatroom.message.enums.HeartbeatRequestMessage;
import com.example.chatroom.message.enums.HeartbeatResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class HeartbeatRequestMessageHandler extends SimpleChannelInboundHandler<HeartbeatRequestMessage> {

    private static final HeartbeatResponseMessage HEARTBEAT_RESPONSE_MESSAGE = new HeartbeatResponseMessage();


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatRequestMessage message) throws Exception {
        ctx.writeAndFlush(HEARTBEAT_RESPONSE_MESSAGE);
    }
}
