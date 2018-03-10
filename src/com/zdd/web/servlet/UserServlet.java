package com.zdd.web.servlet;

import com.zdd.constant.Constant;
import com.zdd.domain.User;
import com.zdd.service.UserService;
import com.zdd.service.impl.UserServiceImpl;
import com.zdd.util.UUIDUtils;
import com.zdd.web.servlet.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Project: store
 * Created by Zdd on 2018/1/17.
 * <p>
 * 用户模块
 */
public class UserServlet extends BaseServlet {


    /**
     * 用户激活
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //1. 接收code
            String code = req.getParameter("code");

            //2. 调用service完成激活
            UserService us = new UserServiceImpl();
            User user = us.active(code);

            //3. 判断user 生成不同的提示信息
            if (null == user) {
//            没有找到这个用户 激活失败
                req.setAttribute("msg", "激活失败，请重新激活或者重新注册");
                return "/jsp/msg.jsp";
            }
//        激活成功
            req.setAttribute("msg", "恭喜您激活成功,可以登录了");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "激活失败，请重新激活或者重新注册");
        }
        return "/jsp/msg.jsp";
    }

    /**
     * 跳转到注册页面
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String registUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    /**
     * 用户注册
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.封装对象
        try {
            User user = new User();
            BeanUtils.populate(user, req.getParameterMap());
            //设置uid
            user.setUid(UUIDUtils.getId());
            //设置是否激活
            user.setState(Constant.USER_IS_NOT_ACTIVE);
            //设置激活码
            user.setCode(UUIDUtils.getCode());

//        2.调用service完成注册
            UserService us = new UserServiceImpl();
            us.register(user);
//        3.页面转发 提示信息
            req.setAttribute("msg", "注册成功，请登录邮箱完成激活");
        } catch (Exception e) {
            e.printStackTrace();
//            转发到msg.jsp
            req.setAttribute("msg", "用户注册失败");
            return "jsp/msg.jsp";
        }


        return "jsp/msg.jsp";
    }
}
