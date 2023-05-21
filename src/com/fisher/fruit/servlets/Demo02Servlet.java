package com.fisher.fruit.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示servlet的生命周期。
 *     init();
 *     servlet();
 *     destroy();
 * @author fisher
 * @version 1.0.1 2023/5/21 - 15:30
 */
public class Demo02Servlet extends HttpServlet {

    /**
     * Tomcat底层可通过反射调用构造方法
     * 完成HttpServlet子类的实例化
     * 进而调用Servlet类中的各种方法
     */
    public Demo02Servlet(){
        System.out.println("正在实例化......");
    }

//    private Demo02Servlet(int port){
//        System.out.println("正在实例化......");
//    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化......");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("正在处理......");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁......");
    }

}
