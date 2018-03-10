package com.zdd.dao;

import com.zdd.domain.User;

import java.sql.SQLException;

/**
 * Project: store
 * Created by Zdd on 2018/1/17.
 */
public interface UserDao {
    void save(User user) throws SQLException;

    User getByCode(String code)throws Exception;

    void update(User user)throws Exception;
}
