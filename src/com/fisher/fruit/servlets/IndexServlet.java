package com.fisher.fruit.servlets;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;
import com.fisher.myssm.myspringmvc.ViewBaseServlet;
import com.fisher.myssm.utils.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 从servlet3.0后，可使用注解（@WebServlet）进行注册。
 * 实现分页显示
 * @author fisher
 * @version 1.2.1 2023/5/23 - 18:36
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws java.io.IOException {
        int pageNo = 1;
        //获取页码
        String pageNoStr = request.getParameter("pageNo");
        if(StringUtil.isNotEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }
        HttpSession session = request.getSession();
        session.setAttribute("pageNo",pageNo);

        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitList(pageNo);
        session.setAttribute("fruitList", fruitList);

        int pageCount = (fruitDao.getFruitCount()+4)/5;
        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index",request,response);
    }
}
