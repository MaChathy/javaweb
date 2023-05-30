package com.fisher.demos.servletdemos;

import com.fisher.bankmgr.dao.BankClientDao;
import com.fisher.bankmgr.dao.BankResourceDao;
import com.fisher.bankmgr.dao.impl.BankClientDaoImpl;
import com.fisher.bankmgr.dao.impl.BankResourceDaoImpl;
import com.fisher.bankmgr.pojo.BankClient;
import com.fisher.bankmgr.pojo.BankResource;
import com.fisher.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author fisher
 * @version 1.0.1 2023/5/30 - 23:27
 */
//@WebServlet("/index")
public class BankMgrServlet extends ViewBaseServlet {

    private BankClientDao bankClientDao = new BankClientDaoImpl();
    private BankResourceDao bankResourceDao = new BankResourceDaoImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<BankResource> bankResources = bankResourceDao.getBankResourcesList();
        List<BankClient> bankClients = bankClientDao.getBankClientList();

        session.setAttribute("clientList", bankClients);
        session.setAttribute("resList", bankResources);

//        processTemplate("index",request,response);
    }
}
