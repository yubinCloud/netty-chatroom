package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.LoginRequestMessage;
import com.example.chatroom.message.enums.LoginResponseMessage;
import com.example.chatroom.server.service.UserService;
import com.example.chatroom.server.service.UserServiceFactory;
import com.example.chatroom.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    private final UserService userService = UserServiceFactory.get();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage message) {
        String username = message.getUsername();
        String password = message.getPassword();
        boolean success = userService.login(username, password);
        LoginResponseMessage responseMessage;
        if (success) {
            SessionFactory.get().bind(ctx.channel(), username);  // 如果登录成功，则将该用户绑定到当前 channel，并记入 session 中
            responseMessage = new LoginResponseMessage(true, "登录成功");
        } else {
            responseMessage = new LoginResponseMessage(false, "用户名或密码错误");
        }
        ctx.writeAndFlush(responseMessage);
    }
}
