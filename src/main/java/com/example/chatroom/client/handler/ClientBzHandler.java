package com.example.chatroom.client.handler;

import com.example.chatroom.client.command.CommandEnum;
import com.example.chatroom.message.Message;
import com.example.chatroom.message.enums.LoginRequestMessage;
import com.example.chatroom.message.enums.LoginResponseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用于处理聊天室 client 的业务逻辑
 */
public class ClientBzHandler extends ChannelInboundHandlerAdapter {

    private final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);  // 用于阻塞住发出登录消息的 client-console 线程

    private final AtomicBoolean LOGIN_SUCCESS = new AtomicBoolean(false);

    /**
     * 接收服务器的响应消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        if (message instanceof LoginResponseMessage responseMessage) {
            if (responseMessage.isSuccess()) {
                LOGIN_SUCCESS.set(true);
            }
            COUNT_DOWN_LATCH.countDown();  // 唤醒 `client-console` 线程
        }
        super.channelRead(ctx, message);
    }


    /**
     * 在连接建立后触发 active 事件
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        new Thread(createClientConsoleTask(ctx), "client-console").start();
    }

    /**
     * 负责与用户在 console 进行交互
     * @return
     */
    private Runnable createClientConsoleTask(ChannelHandlerContext ctx) {
        Scanner scanner = new Scanner(System.in);
        return () -> {
            try {
                loginProcess(ctx, scanner);
            } catch (InterruptedException e) {
                System.out.println("$ 线程被打断，正在结束...");
                throw new RuntimeException();
            }
        };
    }

    private void loginProcess(ChannelHandlerContext ctx, Scanner scanner) throws InterruptedException {
        System.out.println("$ 请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("$ 请输入密码：");
        String password = scanner.nextLine();
        // 构造消息对象并发送
        Message message = new LoginRequestMessage(username, password);
        ctx.writeAndFlush(message);
        // 等待服务器响应，并打印结果
        COUNT_DOWN_LATCH.await();
        if (!LOGIN_SUCCESS.get()) {
            ctx.channel().close();  // 登录失败，则直接关闭
        } else {
            System.out.println("$ 登录成功");
        }
        while (true) {
            printPrompt();
            String command = scanner.nextLine();  // 假设用户输入的命令一定是正确的
            String[] args = command.split("\s+");
            String commandName = args[0].toUpperCase();
            if ("QUIT".equals(commandName)) {
                ctx.channel().close();
                return;
            }
            CommandEnum.valueOf(commandName).getHandler().handle(ctx, username, args);
        }
    }

    private void printPrompt() {
        System.out.println("=========== PROMPT  =============");
        System.out.printf("\uD83D\uDCDE %s [username] [content]\n", CommandEnum.SEND.getValue());
        System.out.printf("\uD83D\uDCDE %s [group name] [content]\n", CommandEnum.GSEND.getValue());
        System.out.printf("\uD83E\uDD73 %s [group name] [m1,m2,m3...]\n", CommandEnum.GCREATE.getValue());
        System.out.printf("\uD83E\uDD73 %s [group name]\n", CommandEnum.GMEMBERS.getValue());
        System.out.printf("\uD83E\uDD1D %s [group name]\n", CommandEnum.GJOIN.getValue());
        System.out.printf("\uD83D\uDC50 %s [group name]\n", CommandEnum.GQUIT.getValue());
        System.out.printf("\uD83D\uDECC %s\n", CommandEnum.QUIT.getValue());
        System.out.println("==================================");
    }
}
