package com.fisher.demoservlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet的初始化
 *      servlet的生命周期：
 *          实例化、初始化。服务、销毁
 * servlet中的ServletContext
 * @author fisher
 * @version 1.1.1 2023/5/28 - 11:26
 */
//@WebServlet(
//        urlPatterns = {"/demo01"},
//        initParams = {
//                @WebInitParam(name = "hello", value = "world"),
//                @WebInitParam(name = "world", value = "Servlet")
//        }
//)
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //获取ServletConfig对象
        ServletConfig servletConfig = getServletConfig();
        //获取初始化参数值
        String initValue = servletConfig.getInitParameter("hello");
        System.out.println("initValue = "+initValue);
        //在servlet初始化时获取ServletContext对象
        ServletContext servletContext = getServletContext();
        //获取初始化参数值
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = "+contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在服务器方法中获取ServletContext对象
        ServletContext servletContext = request.getServletContext();
        //获取初始化参数值
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = "+contextConfigLocation);
        //通过session获取ServletContext对象
        String contextConfigLocation1 = request.getSession().getServletContext().getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation1 = " + contextConfigLocation1);
    }
}
