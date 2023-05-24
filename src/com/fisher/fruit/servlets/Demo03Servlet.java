package com.fisher.fruit.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示application的作用范围（一次应用程序内有效）
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 13:39
 */
public class Demo03Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //向application保存作用域保存数据
        //ServletContext:servlet上下文
        ServletContext application = req.getServletContext();
        application.setAttribute("application","lili");
    }
}
