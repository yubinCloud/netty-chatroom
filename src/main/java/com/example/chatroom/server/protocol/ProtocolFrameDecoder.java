package com.example.chatroom.server.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 对 LengthFieldBasedFrameDecoder 的封装，使得 client 与 server 可以共用
 */
public class ProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProtocolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    public ProtocolFrameDecoder() {
        this(1024, 12, 4, 0, 0);
    }

}
