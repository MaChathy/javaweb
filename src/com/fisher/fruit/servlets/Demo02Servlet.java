package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示一次请求响应的保存作用域
 * 配合Demo01Servlet使用
 * @author fisher
 * @version 1.0.1 2023/5/24 - 9:52
 */
@WebServlet("/demo02")
public class Demo02Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request保存作用域保存的数据，并显示在网页页面上
        response.getWriter().write("Hello"+request.getParameter("username"));
    }
}
