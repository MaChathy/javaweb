package com.fisher.fruit.servlets;

import com.fisher.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This program demonstrates se_projects
 *
 * @author fisher
 * @version 1.0.1 2023/5/26 - 13:42
 */
@WebServlet("/hello")
public class HelloServlet extends ViewBaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取session作用域
        HttpSession session = req.getSession();
        //向session作用域中保存greet
        session.setAttribute("greet","hello world");

        super.processTemplate("/hello",req,resp);
    }
}
