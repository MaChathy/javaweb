package com.fisher.fruit.controllers;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;
import com.fisher.myssm.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * FruitController组件，重写service方法,不再是Servlet组件
 *      包括：增、删、改、查
 *
 * v1.4.1 之前FruitServlet是一个servlet组件，则init()方法一定会被调用
 *        现在FruitServlet不是一个servlet组件，是一个Controller组件，则init()方法不会被调用，则super.init()方法不会被调用
 * v1.5.1 FruitServlet不再继承ViewBaseServlet，视图处理
 * @author fisher
 * @version 1.5.1 2023/5/27 - 14:52
 */
public class FruitController{

    private FruitDao fruitDao = new FruitDaoImpl();

    /*
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取客户端需求
        String method = request.getParameter("method");
        if(StringUtil.isEmpty(method)){
            method = "index";
        }
        //获取当前类中所有的方法（method）
        Method[] methods = this.getClass().getDeclaredMethods();
        boolean hasMethod = false;
        //通过反射调用方法
        for(Method m : methods){
            //获取方法名,并判断客户需求是否等于某个方法名
            if(method.equals(m.getName())){
                hasMethod = true;
                //通过反射调用方法
                try {
                    m.setAccessible(true);
                    m.invoke(this, request, response);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!hasMethod)
            throw new RuntimeException("Method值:" + method+"非法！");
    }
     */

    //显示页面方法
    private String index(HttpServletRequest request) {
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
//        super.processTemplate("index",request,response);
        return "index";
    }

    //添加水果库存信息方法
    private String addFruit(HttpServletRequest request){

        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(0,fname,price,fcount,remark);
        fruitDao.addFruit(fruit);

//        response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    //编辑水果库存信息
    private String editFruit(HttpServletRequest req) {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruitByFid = fruitDao.getFruitByFid(fid);
            req.setAttribute("afruit", fruitByFid);
            //数据渲染
//            super.processTemplate("edit",req,resp);
            return "edit";
        }
        return "error";
    }

    //更新水果信息
    protected String updateFruit(HttpServletRequest request) {
        //1.获取表单信息
        int fid = Integer.parseInt(request.getParameter("fid"));
        String fname = request.getParameter("fname");
        int price = Integer.parseInt(request.getParameter("price"));
        int fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        //2.执行更新
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //3.资源跳转，返回index页面;客户端重定向
        //response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    //删除水果信息
    private String delFruit(HttpServletRequest request) {
        int fid = Integer.parseInt(StringUtil.isNotEmpty(request.getParameter("fid"))? request.getParameter("fid"):"-1");
        fruitDao.delFruit(fid);
        //服务器内部转发
//        super.processTemplate("fruit.do").forward(request,response);
        //客户端重定向
//        response.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

}
