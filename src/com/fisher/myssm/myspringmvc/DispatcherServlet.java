package com.fisher.myssm.myspringmvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 调度器--处理所有以".do"结尾的请求
 * @author fisher
 * @version 1.0.1 2023/5/27 - 17:08
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        /*
            TODO:
                1.获取请求路径  getServletPath(); e.g. /fruit.do
                2.去除请求后缀，将请求路径拆分为请求名 e.g. /fruit.do -> fruit
                3.根据请求名，获取相应的Controller，e.g. fruit -> fruitController
                4.创建配置文件，说明对用关系
                4.获取请求参数
         */
        int lastDotIndex = request.getServletPath().lastIndexOf(".do");
        String servletName = request.getServletPath().substring(1, lastDotIndex);

    }
}
