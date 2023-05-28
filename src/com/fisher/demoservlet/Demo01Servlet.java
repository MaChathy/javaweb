package com.fisher.demoservlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * servlet的初始化
 *      servlet的生命周期：
 *          实例化、初始化。服务、销毁
 * @author fisher
 * @version 1.0.1 2023/5/28 - 11:26
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
    }
}
