package com.example.chatroom.client;

import com.example.chatroom.server.protocol.MessageCodec;
import com.example.chatroom.server.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatClient {

    public static void main(String[] args) {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);

        try (NioEventLoopGroup group = new NioEventLoopGroup()) {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());  // 将收到的字节流基于 LTC Decoder 进行组装或切分，使之成为一个个完整的包，解决半包和粘包的问题
                    ch.pipeline().addLast(LOGGING_HANDLER);  // 日志记录
                    ch.pipeline().addLast(new MessageCodec());  // 实现 bytes 与实体类 message 的编解码
                }
            });
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException exception) {
            log.error("client error. reason: ", exception);
        }
    }

}