package com.zdd.service.impl;

import com.zdd.dao.UserDao;
import com.zdd.dao.impl.UserDaoImpl;
import com.zdd.domain.User;
import com.zdd.service.UserService;
import com.zdd.util.MailUtils;

/**
 * Project: store
 * Created by Zdd on 2018/1/17.
 */
public class UserServiceImpl implements UserService {

    @Override
    public void register(User user) throws Exception {
        //调用dao完成注册
        UserDao ud = new UserDaoImpl();
        ud.save(user);
        //发送激活邮件
        String emailMsg="恭喜"+user.getName()+":成为我们商城的一员，点此激活。";
        MailUtils.sendMail(user.getEmail(), emailMsg);
    }

    @Override
    public User active(String code) throws Exception {
        return null;
    }

    @Override
    public User login(String username, String password) throws Exception {
        return null;
    }
}
