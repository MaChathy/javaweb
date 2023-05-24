package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示application的作用域范围（一次应用程序内有效）
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 13:39
 */
@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取application保存作用域中的数据
        Object application = req.getServletContext().getAttribute("application");
        resp.getWriter().write(""+application);

    }
}
