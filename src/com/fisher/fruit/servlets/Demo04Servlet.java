package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Session对象的保存作用域
 *  演示向HttpSession保存数据
 * @author fisher
 * @version 1.0.1 2023/5/21 - 18:38
 */
public class Demo04Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getSession().setAttribute("uname","李冲");
    }
}
