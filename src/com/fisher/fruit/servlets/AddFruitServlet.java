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
 * This program demonstrates se_projects
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 17:54
 */
@WebServlet("addFruit.do")
public class AddFruitServlet extends ViewBaseServlet {

    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0,fname,price,fcount,remark);
        fruitDao.addFruit(fruit);

        response.sendRedirect("index");
    }
}
