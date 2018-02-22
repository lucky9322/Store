package com.zdd.service;

import com.zdd.domain.User;

/**
 * Project: store
 * Created by Zdd on 2018/1/17.
 */
public interface UserService {
    void register(User user) throws Exception;

    User active(String code) throws Exception;

    User login(String username, String password) throws Exception;
}
