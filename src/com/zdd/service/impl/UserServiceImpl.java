package com.zdd.service.impl;

import com.zdd.constant.Constant;
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
        String emailMsg="恭喜"+user.getName()+":成为我们商城的一员，<a href='http://localhost/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
        MailUtils.sendMail(user.getEmail(), emailMsg);
    }

    @Override
    public User active(String code) throws Exception {
//        1. 通过code 获取用户
        UserDao ud=new UserDaoImpl();
        User user=ud.getByCode(code);
//        1.1 通过激活码没有找到用户
        if (null==user)
            return null;

//        2.如果获取到了用户
        user.setState(Constant.USER_IS_ACTIVE);
        user.setCode(null);
        ud.update(user);
        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        return null;
    }
}
