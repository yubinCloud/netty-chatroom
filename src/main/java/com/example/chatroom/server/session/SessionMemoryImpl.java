package com.example.chatroom.server.session;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class SessionMemoryImpl implements Session {

    private final Map<String, Channel> userMapping = new ConcurrentHashMap<>();

    private final Map<Channel, String> channelMapping = new ConcurrentHashMap<>();

    private final Map<Channel, Map<String, Object>> channelAttributeMapping = new ConcurrentHashMap<>();

    @Override
    public void bind(Channel channel, String username) {
        userMapping.put(username, channel);
        channelMapping.put(channel, username);
        channelAttributeMapping.remove(channel);
    }

    @Override
    public void unbind(Channel channel) {
        String username = channelMapping.remove(channel);
        userMapping.remove(username);
        channelAttributeMapping.remove(channel);
    }

    @Override
    public Object getAttribute(Channel channel, String name) {
        var attr = channelAttributeMapping.get(channel);
        if (Objects.isNull(attr)) {
            return null;
        }
        return attr.get(name);
    }

    @Override
    public void setAttribute(Channel channel, String name, Object value) {
        channelAttributeMapping.get(channel).put(name, value);
    }

    @Override
    public Channel getChannel(String username) {
        return userMapping.get(username);
    }

    @Override
    public String toString() {
        return userMapping.toString();
    }
}
