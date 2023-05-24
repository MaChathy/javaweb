package com.fisher.fruit.servlets;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;
import com.fisher.myssm.myspringmvc.ViewBaseServlet;
import com.fisher.myssm.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 从servlet3.0后，可使用注解（@WebServlet）进行注册。
 * 全部水果信息实现分页显示
 * 查询关键字信息实现分页显示
 * @author fisher
 * @version 1.3.1 2023/5/23 - 18:36
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws java.io.IOException {

        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //页码初始值为1
        int pageNo = 1;

        HttpSession session = request.getSession();


        //获取表单操作
        String operate = request.getParameter("operate");

        String keyword = null;
        //若operate不为null,则operate的值为查询表单按钮
        if (StringUtil.isNotEmpty(operate) && "search".equals(operate)){
            //此时，pageNo应还原为1，keyword应从session作用域中获取
            keyword = request.getParameter("keyword");
            pageNo = 1;
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            //将keyword保存到session作用域中
            session.setAttribute("keyword",keyword);

        }else{//此处非表单查询发送来的请求

            //获取页码
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            //此时keyword应从session作用中获取
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }
        }
        //设置页码
        session.setAttribute("pageNo",pageNo);

        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitList(keyword,pageNo);

        session.setAttribute("fruitList", fruitList);

        int pageCount = (fruitDao.getFruitCount(keyword)+4)/5;
        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index",request,response);
    }
}
