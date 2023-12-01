package com.example.chatroom.server.handler;

import com.example.chatroom.message.enums.GroupCreateRequestMessage;
import com.example.chatroom.message.enums.GroupCreateResponseMessage;
import com.example.chatroom.server.session.Group;
import com.example.chatroom.server.session.GroupSession;
import com.example.chatroom.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage message) throws Exception {
        // 所要创建的群的信息
        String groupName = message.getGroupName();
        var members = message.getMembers();
        GroupSession groupSession = GroupSessionFactory.get();  // 群管理器
        Group group = groupSession.createGroup(groupName, members);
        if (Objects.nonNull(group)) {
            // 发送创建成功的消息
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, "群聊 " + groupName + " 创建成功"));
            // 通知每个人被拉入群中的消息
            groupSession.getMembersChannel(groupName).forEach(channel -> {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您被拉入 " + groupName));
            });
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
        }
    }
}
