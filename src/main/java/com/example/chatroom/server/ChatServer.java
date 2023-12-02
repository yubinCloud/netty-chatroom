package com.example.chatroom.server;

import com.example.chatroom.message.MessageEnum;
import com.example.chatroom.protocol.MessageCodec;
import com.example.chatroom.protocol.ProtocolFrameDecoder;
import com.example.chatroom.server.handler.IdleStateReaderIdleEvenHandler;
import com.example.chatroom.server.handler.QuitHandler;
import com.example.chatroom.server.handler.factory.ServerHandlerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatServer {

    private static final QuitHandler QUIT_HANDLER = new QuitHandler();

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
                    ch.pipeline().addLast(QUIT_HANDLER);  // 处理 client 断开的事件
                    // IdleStateHandler 用来判断是否读写空闲时间过长
                    // 如果 5s 内没有收到 channel 的数据，则会触发一个 IdleState READER_IDLE 事件
                    ch.pipeline().addLast(new IdleStateHandler(5, 0, 0));
                    ch.pipeline().addLast(new IdleStateReaderIdleEvenHandler());  // 处理 IdleState READER_IDLE 事件
                    // 添加各 simple channel inbound handler，每种 handler 负责处理一类 message
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.LOGIN_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_CREATE_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_MEMBERS_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_JOIN_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_QUIT_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.GROUP_CHAT_REQUEST_MESSAGE));
                    ch.pipeline().addLast(ServerHandlerFactory.createMessageHandler(MessageEnum.HEARTBEAT_REQUEST_MESSAGE));
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server bootstrap error. reason: ", e);
        }
    }
}
