package com.example.chatroom.server.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 处理 IdleStateHandler 抛出的 READER_IDLE 事件，解决 channel 长时间没有接收到数据的情况
 *
 * 继承 ChannelDuplexHandler 从而可以同时处理 inbound 和 outbound
 */
public class IdleStateReaderIdleEvenHandler extends ChannelDuplexHandler {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (!(evt instanceof IdleStateEvent)) {
            return;
        }
        IdleStateEvent event = (IdleStateEvent) evt;
        // 如果触发了“读空闲”事件，则发送心跳包
        if (!(event.state().equals(IdleState.READER_IDLE))) {
            return;
        }
        // 发生心跳包，检测 client 是否还活着

    }
}
