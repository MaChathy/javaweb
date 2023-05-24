package com.fisher.fruit.servlets;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;
import com.fisher.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新水果库存信息表组件
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 16:22
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("UTF-8");

        //2.获取表单信息
        int fid = Integer.parseInt(request.getParameter("fid"));
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        //3.执行更新
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //4.资源跳转，返回index页面
//        super.processTemplate("index",request,response);
        //request.getResponseDispatcher("index.html").forward(request,response);
        //客户端重定向
        response.sendRedirect("index");
    }
}
