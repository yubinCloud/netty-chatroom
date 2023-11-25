package com.example.chatroom.server.session;

import io.netty.channel.Channel;
import java.util.List;
import java.util.Set;

/**
 * 聊天组会话管理窗口
 */
public interface GroupSession {

    /**
     * 创建一个聊天组，如果不存在才能创建成功，否则返回 null
     * @param name
     * @param members
     * @return
     */
    Group createGroup(String name, Set<String> members);

    /**
     * 将一个成员加入聊天组
     * @param name
     * @param member
     * @return 如果聊天组不存在，则返回 null
     */
    Group joinMember(String name, String member);

    /**
     * 移除聊天组的成员
     * @param name
     * @param member
     * @return
     */
    Group removeMember(String name, String member);

    /**
     * 移除聊天组
     * @param name
     * @return
     */
    Group removeGroup(String name);

    /**
     * 获取组成员
     * @param name
     * @return
     */
    Set<String> getMembers(String name);

    /**
     * 获取组成员的 channel 集合，只有在线的 channel 才会返回
     * @param name
     * @return
     */
    List<Channel> getMembersChannel(String name);
}
