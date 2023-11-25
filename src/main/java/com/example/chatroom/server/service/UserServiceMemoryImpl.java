package com.example.chatroom.server.service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户系统的内存实现
 */
public class UserServiceMemoryImpl implements UserService {

    private static final Map<String, String> repo = new ConcurrentHashMap<>();

    static {
        repo.put("yubin", "123456");
        repo.put("nn", "123456");
        repo.put("Eva", "123456");
        repo.put("Daniel", "123456");
    }

    @Override
    public boolean login(String username, String password) {
        String realPassword = repo.get(password);
        return !Objects.isNull(realPassword) && password.equals(realPassword);
    }
}
