package com.fisher.fruit.servlets;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;
import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  Add.do 工件
 * @author fisher
 * @version 1.0.1 2023/5/20 - 19:37
 */
@WebServlet("/add.do")
public class AddServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException {
        //post方式下设置编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String account = request.getParameter("account");
        String PASSWORD = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        PrintWriter writer = response.getWriter();

        System.out.println(id + " "+ account + " " + PASSWORD + " "+ nickname);
        writer.write("接收信息成功");
        writer.close();
    }
}
