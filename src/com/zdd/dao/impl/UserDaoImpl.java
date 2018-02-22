package com.zdd.dao.impl;

import com.zdd.dao.UserDao;
import com.zdd.domain.User;
import com.zdd.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

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
}
