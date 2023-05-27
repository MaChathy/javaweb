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
 * v1.6.1 FruitServlet 参数注入
 * @author fisher
 * @version 1.6.1 2023/5/28 00:39:34
 */
public class FruitController{

    private FruitDao fruitDao = new FruitDaoImpl();

    //显示页面方法
    private String index(String operate, String keyword,Integer pageNo,HttpServletRequest request) {
        HttpSession session = request.getSession();

        pageNo = pageNo == null ? 1 : pageNo;

        if (StringUtil.isNotEmpty(operate) && "search".equals(operate)){
            //此时，pageNo应还原为1，keyword应从session作用域中获取
            pageNo = 1;
            //若keyword为null，需要设置字符串为"".否则查询会拼接成“%null%”
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            //将keyword(覆盖)保存到session作用域中
            session.setAttribute("keyword",keyword);
        }else{
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
        return "index";
    }

    //添加水果库存信息方法
    private String addFruit(String fname,Integer price,Integer fcount,String remark){
        Fruit fruit = new Fruit(0,fname,price,fcount,remark);
        fruitDao.addFruit(fruit);
        return "redirect:fruit.do";
    }

    //编辑水果库存信息
    private String editFruit(Integer fid,HttpServletRequest request) {
        if(fid != null){
            Fruit fruitByFid = fruitDao.getFruitByFid(fid);
            request.setAttribute("afruit", fruitByFid);
            return "edit";
        }
        return "error";
    }

    //更新水果信息
    private String updateFruit(Integer fid,String fname,Integer price,Integer fcount,String remark) {
        //执行更新
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //资源跳转，返回index页面;客户端重定向
        return "redirect:fruit.do";
    }

    //删除水果信息
    private String delFruit(Integer fid, HttpServletRequest request) {
        if (fid != null){
            fruitDao.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }
}
