package com.example.chatroom.server.session;


import io.netty.channel.Channel;

/**
 * Session 用于记录 user 和 channel 的对应关系
 */
public interface Session {

    /**
     * 绑定会话
     * @param channel
     * @param username
     */
    void bind(Channel channel, String username);

    /**
     * 解绑会话
     * @param channel
     */
    void unbind(Channel channel);

    /**
     * 获取属性
     * @param channel
     * @param name
     * @return
     */
    Object getAttribute(Channel channel, String name);

    /**
     * 设置属性
     * @param channel
     * @param name
     * @param value
     */
    void setAttribute(Channel channel, String name, Object value);

    /**
     * 根据用户名获取 channel
     * @param username
     * @return
     */
    Channel getChannel(String username);
}
