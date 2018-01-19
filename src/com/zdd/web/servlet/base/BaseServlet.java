package com.zdd.web.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Project: Store
 * Created by Zdd on 2018/1/18.
 */
public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
//        1.获取方法名
            String mName = req.getParameter("method");
//            1.1 判断 参数是否为空 如果为空 执行默认的方法
            if (null == mName || mName.trim().equals("")) {
                mName = "index";
            }


//        2.获取方法对象
            Method method = this.getClass().getMethod(mName, HttpServletRequest.class, HttpServletResponse.class);

//          3.  让方法执行，接收返回值
            String path = (String) method.invoke(this, req, resp);

//            4.判断返回值是否为空， 如果不是空 统一处理请求转发
            if (null != path) {
                req.getRequestDispatcher(path).forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("");
        }

    }

    public String index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("亲，不要捣乱");
        return null;
    }
}
