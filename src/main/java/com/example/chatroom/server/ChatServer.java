package com.example.chatroom.server;

import com.example.chatroom.server.protocol.MessageCodec;
import com.example.chatroom.server.protocol.ProtocolFrameDecoder;
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
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());  // 将收到的字节流基于 LTC Decoder 进行组装或切分，使之成为一个个完整的包，解决半包和粘包的问题
                    ch.pipeline().addLast(LOGGING_HANDLER);  // 日志记录
                    ch.pipeline().addLast(new MessageCodec());  // 实现 bytes 与实体类 message 的编解码
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server bootstrap error. reason: ", e);
        }
    }
}
