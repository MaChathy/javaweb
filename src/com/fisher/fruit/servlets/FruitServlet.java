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
 * This program demonstrates se_projects
 *
 * @author fisher
 * @version 1.0.1 2023/5/27 - 14:52
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {

    private FruitDao fruitDao = new FruitDaoImpl();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String method = request.getParameter("method");
        if(StringUtil.isEmpty(method)){
            method = "index";
        }
        switch (method){
            case "index":
                index(request, response);
                break;
            case "addFruit.do":
                addFruit(request, response);
                break;
            case "edit.do":
                editFruit(request, response);
                break;
            case "del.do":
                delFruit(request, response);
                break;
            case "update.do":
                updateFruit(request, response);
                break;
        }

    }
    //显示页面方法
    private void index(HttpServletRequest request, HttpServletResponse response)
            throws java.io.IOException {
        HttpSession session = request.getSession();

        //页码初始值为1
        int pageNo = 1;

        //获取表单操作
        String operate = request.getParameter("operate");

        //若operate不为null,则operate的值为查询表单按钮
        String keyword = null;

        if (StringUtil.isNotEmpty(operate) && "search".equals(operate)){
            //此时，pageNo应还原为1，keyword应从session作用域中获取
            keyword = request.getParameter("keyword");
            pageNo = 1;
            //若keyword为null，需要设置字符串为"".否则查询会拼接成“%null%”
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            //将keyword(覆盖)保存到session作用域中
            session.setAttribute("keyword",keyword);

        }else{
            //此处非表单查询发送来的请求
            //获取页码
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            //keyword应从session作用中获取
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            }else{
                keyword = "";
            }
        }
        //更新当前页码的值
        session.setAttribute("pageNo",pageNo);

        FruitDao fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getFruitList(keyword,pageNo);

        session.setAttribute("fruitList", fruitList);

        int pageCount = (fruitDao.getFruitCount(keyword)+4)/5;
        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index",request,response);
    }
    //添加水果库存信息方法
    private void addFruit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0,fname,price,fcount,remark);
        fruitDao.addFruit(fruit);

        response.sendRedirect("index");
    }

    //编辑水果库存信息
    private void editFruit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruitByFid = fruitDao.getFruitByFid(fid);
            req.setAttribute("afruit", fruitByFid);
            //数据渲染
            super.processTemplate("edit",req,resp);

        }
    }
    //更新水果信息
    protected void updateFruit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取表单信息
        int fid = Integer.parseInt(request.getParameter("fid"));
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        //2.执行更新
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //3.资源跳转，返回index页面
//        super.processTemplate("index",request,response);
        //request.getResponseDispatcher("index.html").forward(request,response);
        //客户端重定向
        response.sendRedirect("index");
    }
    //删除水果信息
    private void delFruit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fid = Integer.parseInt(request.getParameter("fid"));
        fruitDao.delFruit(fid);
        //客户端重定向
        response.sendRedirect("index");
    }

}
