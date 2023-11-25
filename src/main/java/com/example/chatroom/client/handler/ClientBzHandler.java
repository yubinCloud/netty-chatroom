package com.example.chatroom.client.handler;

import com.example.chatroom.message.Message;
import com.example.chatroom.message.enums.LoginRequestMessage;
import com.example.chatroom.message.enums.LoginResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

/**
 * 用于处理聊天室 client 的业务逻辑
 */
public class ClientBzHandler extends ChannelInboundHandlerAdapter {

    /**
     * 接收服务器的响应消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        System.out.println("收到消息：" + message);
    }


    /**
     * 在连接建立后触发 active 事件
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        new Thread(createClientConsoleTask(ctx), "client-console-thread").start();
    }

    /**
     * 负责与用户在 console 进行交互
     * @return
     */
    private Runnable createClientConsoleTask(ChannelHandlerContext ctx) {
        Scanner scanner = new Scanner(System.in);
        return () -> {
            System.out.println("请输入用户名：");
            String username = scanner.nextLine();
            System.out.println("请输入密码：");
            String password = scanner.nextLine();
            // 构造消息对象并发送
            Message message = new LoginRequestMessage(username, password);
            ctx.writeAndFlush(message);
        };
    }
}
