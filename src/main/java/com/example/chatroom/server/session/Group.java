package com.example.chatroom.server.session;

import lombok.Data;

import java.util.Collections;
import java.util.Set;

/**
 * 聊天组，即聊天室
 */
@Data
public class Group {

    // 聊天室名称，约定此 name 必须唯一
    private String name;

    // 聊天室成员
    private Set<String> members;

    public static final Group EMPTY_GROUP = new Group("empty", Collections.emptySet());

    public Group(String name, Set<String> members) {
        this.name = name;
        this.members = members;
    }
}
