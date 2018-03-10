package com.zdd.dao.impl;

import com.zdd.dao.UserDao;
import com.zdd.domain.User;
import com.zdd.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Project: store
 * Created by Zdd on 2018/1/17.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) throws SQLException {
        /*
         *  `uid` varchar(32) NOT NULL,
			  `username` varchar(20) DEFAULT NULL,
			  `password` varchar(20) DEFAULT NULL,

			  `name` varchar(20) DEFAULT NULL,
			  `email` varchar(30) DEFAULT NULL,
			  `telephone` varchar(20) DEFAULT NULL,

			  `birthday` date DEFAULT NULL,
			  `sex` varchar(10) DEFAULT NULL,
			  `state` int(11) DEFAULT NULL,

			  `code` varchar(64) DEFAULT NULL,
		 */
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?);";
        qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(),
                user.getName(), user.getEmail(),
                user.getBirthday(), user.getBirthday(), user.getSex(), user.getState(),
                user.getCode());
    }

    /**
     * 通过激活码获取用户
     *
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public User getByCode(String code) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where code = ? limit 1";
        return qr.query(sql, new BeanHandler<User>(User.class), code);
    }

    /**
     * 更新用户
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void update(User user) throws Exception {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set password = ? , sex = ? , state = ? where uid = ? ";
        qr.update(sql, user.getPassword(), user.getSex(), user.getState(), user.getUid());
    }
}
