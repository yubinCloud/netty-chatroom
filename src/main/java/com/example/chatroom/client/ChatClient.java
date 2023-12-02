package com.example.chatroom.client;

import com.example.chatroom.client.handler.*;
import com.example.chatroom.protocol.MessageCodec;
import com.example.chatroom.protocol.ProtocolFrameDecoder;
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

    private static final ChatMessageHandler CHAT_MESSAGE_HANDLER = new ChatMessageHandler();
    private static final GroupCreateResponseMessageHandler GROUP_CREATE_RESPONSE_MESSAGE_HANDLER = new GroupCreateResponseMessageHandler();
    private static final GroupMembersResponseMessageHandler GROUP_MEMBERS_RESPONSE_MESSAGE_HANDLER = new GroupMembersResponseMessageHandler();
    private static final GroupJoinResponseMessageHandler GROUP_JOIN_RESPONSE_MESSAGE_HANDLER = new GroupJoinResponseMessageHandler();
    private static final GroupQuitResponseMessageHandler GROUP_QUIT_RESPONSE_MESSAGE_HANDLER = new GroupQuitResponseMessageHandler();
    private static final GroupChatResponseMessageHandler GROUP_CHAT_RESPONSE_MESSAGE_HANDLER = new GroupChatResponseMessageHandler();
    private static final HeartbeatRequestMessageHandler HEARTBEAT_REQUEST_MESSAGE_HANDLER = new HeartbeatRequestMessageHandler();

    public static void main(String[] args) {
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());  // 将收到的字节流基于 LTC Decoder 进行组装或切分，使之成为一个个完整的包，解决半包和粘包的问题
//                    ch.pipeline().addLast(LOGGING_HANDLER);  // 日志记录
                    ch.pipeline().addLast(new MessageCodec());  // 实现 bytes 与实体类 message 的编解码
                    ch.pipeline().addLast("bz handler", new ClientBzHandler());  // client 的业务 handler
                    ch.pipeline().addLast(CHAT_MESSAGE_HANDLER);
                    ch.pipeline().addLast(GROUP_CREATE_RESPONSE_MESSAGE_HANDLER);
                    ch.pipeline().addLast(GROUP_MEMBERS_RESPONSE_MESSAGE_HANDLER);
                    ch.pipeline().addLast(GROUP_JOIN_RESPONSE_MESSAGE_HANDLER);
                    ch.pipeline().addLast(GROUP_QUIT_RESPONSE_MESSAGE_HANDLER);
                    ch.pipeline().addLast(GROUP_CHAT_RESPONSE_MESSAGE_HANDLER);
                    ch.pipeline().addLast(HEARTBEAT_REQUEST_MESSAGE_HANDLER);
                }
            });
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            channel.closeFuture().sync();  // 当 channel 调用 close() 时会触发这一行的继续执行
        } catch (InterruptedException exception) {
            log.error("client error. reason: ", exception);
        } finally {
            group.shutdownGracefully();
        }
    }

}
