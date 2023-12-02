package com.example.chatroom.server;

import com.example.chatroom.message.MessageEnum;
import com.example.chatroom.protocol.MessageCodec;
import com.example.chatroom.protocol.ProtocolFrameDecoder;
import com.example.chatroom.server.handler.factory.ServerHandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatServer {

    public static void main(String[] args) {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);

        try (
            NioEventLoopGroup boss = new NioEventLoopGroup();
            NioEventLoopGroup worker = new NioEventLoopGroup()
        ) {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);  // 两个 group，boss 处理 Accept 事件，worker 处理读写事件
            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());  // 将收到的字节流基于 LTC Decoder 进行组装或切分，使之成为一个个完整的包，解决半包和粘包的问题
//                    ch.pipeline().addLast(LOGGING_HANDLER);  // 日志记录
                    ch.pipeline().addLast(new MessageCodec());  // 实现 bytes 与实体类 message 的编解码
                    // 添加各 simple channel inbound handler，每种 handler 负责处理一类 message
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.LOGIN_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_CREATE_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_MEMBERS_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_JOIN_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_QUIT_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_CHAT_REQUEST_MESSAGE));
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server bootstrap error. reason: ", e);
        }
    }
}
