package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 服务器内部转发与内部重定向
 *      服务器内部重定向
 * @author fisher
 * @version 1.0.1 2023/5/23 - 17:01
 */
@WebServlet("/demo07")
public class Demo07Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.getWriter().write("Welcome to demo07");
        System.out.println("demo07...");
    }
}
