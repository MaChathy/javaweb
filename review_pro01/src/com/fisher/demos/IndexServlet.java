package com.fisher.demos;

import com.fisher.bankmgr.dao.BankClientDao;
import com.fisher.bankmgr.dao.impl.BankClientDaoImpl;
import com.fisher.bankmgr.pojo.BankClient;

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
 * @version 1.0.1 2023/5/29 - 15:38
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private BankClientDao bankClientDao = new BankClientDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<BankClient> bankClientList = bankClientDao.getBankClientList();
        session.setAttribute("clientList", bankClientList);
        req.getRequestDispatcher("index.html").forward(req, resp);
    }
}
