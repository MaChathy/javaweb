package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 服务器内部转发与客户端内部重定向
 *      1.服务器内部转发
 *      2.客户端内部重定向
 * @author fisher
 * @version 1.0.1 2023/5/23 - 16:59
 */
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo06...");
        //1.服务器内部转发
        request.getRequestDispatcher("demo07").forward(request, response);
        //2.客户端重定向
//        response.sendRedirect("demo07");
    }

}
