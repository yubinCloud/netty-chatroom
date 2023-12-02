package com.example.chatroom.server.handler;

import com.example.chatroom.server.session.Session;
import com.example.chatroom.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 用于处理 client 退出的操作
 * 由于 quit 不是一个消息而是一个事件，因此这里 QuitHandler 没有继承 SimpleChannelInboundHandler 而是继承了 ChannelInboundHandlerAdapter
 */
@ChannelHandler.Sharable
public class QuitHandler extends ChannelInboundHandlerAdapter {

    private static final Session session = SessionFactory.get();

    /**
     * 当连接断开时触发 inactivate 事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        var currentChannel = ctx.channel();  // 当前与 client 进行会话的 channel
        String username = session.unbind(currentChannel);
        System.out.printf("$ %s 已经断开\n", username);
    }

    /**
     * 当 client 因发生异常而直接断掉触发的事件
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        var currentChannel = ctx.channel();  // 当前与 client 进行会话的 channel
        String username = session.unbind(currentChannel);
        System.out.printf("$ %s 已经异常断开\n", username);
    }
}
