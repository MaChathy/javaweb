package com.fisher.fruit.servlets;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;
import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
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
public class AddServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            IOException, ServletException {
        //post方式下设置编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String fName = request.getParameter("fName");
        int price =Integer.parseInt(request.getParameter("price"));
        int fCount = Integer.parseInt(request.getParameter("fCount"));
        String remark = request.getParameter("remark");
        PrintWriter writer = response.getWriter();

        System.out.println("名称："+fName+"\n价格："+price+"\n库存："+fCount+"\n备注："+remark);
        writer.write("接收信息成功");
        writer.close();
        //FruitDao fruitDao = new FruitDaoImpl();
        //boolean flag = fruitDao.addFruit(new Fruit(0, fName, price, fCount, remark));
        //System.out.println(flag?"添加成功":"添加失败");
    }
}
