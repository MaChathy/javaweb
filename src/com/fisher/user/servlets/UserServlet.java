package com.fisher.user.servlets;

import com.fisher.myssm.myspringmvc.ViewBaseServlet;
import com.fisher.user.dao.UserDao;
import com.fisher.user.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * thymeleaf简单测试。
 * @author fisher
 * @version 1.0.1 2023/5/24 - 10:49
 */
@WebServlet("/user_index")
public class UserServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            IOException {
        UserDao userDao = new UserDaoImpl();
        HttpSession session = req.getSession();
        session.setAttribute("userList", userDao.getUserList());
        //服务器内部转发
//        req.getRequestDispatcher("add.do").forward(req,resp);
        //数据渲染
        super.processTemplate("user_index",req,resp);
    }
}
