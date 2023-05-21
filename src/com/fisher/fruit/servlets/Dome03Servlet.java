package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 演示Session5
 * TODO:
 *     1.获取Session
 *     2.输出Session id
 * @author fisher
 * @version 1.0.1 2023/5/21 - 17:41
 */
public class Dome03Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取Session，若获取不到，则创建新的Session
        HttpSession session = request.getSession();
        //输出Session id
        System.out.println("Session id: " + session.getId());
        //Session会话对象的非激活间隔时长默认为30min
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println(maxInactiveInterval);
    }
}
