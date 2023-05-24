package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示一次请求响应的保存作用域
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 9:50
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //向request保存作用域保存数据
        request.setAttribute("username", "fisher");
        //客户端重定向
        response.sendRedirect("demo02");

    }
}
