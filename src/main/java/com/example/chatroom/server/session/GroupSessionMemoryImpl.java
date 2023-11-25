package com.example.chatroom.server.session;

import io.netty.channel.Channel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GroupSessionMemoryImpl implements GroupSession {

    private final Map<String, Group> groupMapping = new ConcurrentHashMap<>();


    @Override
    public Group createGroup(String name, Set<String> members) {
        Group group = new Group(name, members);
        return groupMapping.putIfAbsent(name, group);
    }

    @Override
    public Group joinMember(String name, String member) {
        var group = Optional.ofNullable(groupMapping.get(name));
        group.ifPresent((gp) -> gp.getMembers().add(member));
        return group.orElse(null);
    }

    @Override
    public Group removeMember(String name, String member) {
        var group = Optional.ofNullable(groupMapping.get(name));
        group.ifPresent((gp) -> gp.getMembers().remove(member));
        return group.orElse(null);
    }

    @Override
    public Group removeGroup(String name) {
        return groupMapping.remove(name);
    }

    @Override
    public Set<String> getMembers(String name) {
        var group = groupMapping.get(name);
        if (Objects.isNull(group)) {
            return Collections.emptySet();
        }
        return group.getMembers();
    }

    @Override
    public List<Channel> getMembersChannel(String name) {
        return getMembers(name).stream()
                .map(member -> SessionFactory.get().getChannel(member))
                .filter(Objects::nonNull)
                .toList();
    }
}
