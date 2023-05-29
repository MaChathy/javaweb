package com.fisher.bankmgr.dao.impl;

import com.fisher.bankmgr.dao.BankClientDao;
import com.fisher.bankmgr.dao.BankResourceDao;
import com.fisher.bankmgr.pojo.BankClient;
import com.fisher.bankmgr.pojo.BankResource;
import org.junit.Test;

import java.util.List;

/**
 * This program demonstrates webfisher
 *
 * @author fisher
 * @version 1.0.1 2023/5/29 - 13:36
 */
public class BankResourceDaoImplTest {
    @Test
    public void testGetList() {
        BankResourceDao bankResourceDao = new BankResourceDaoImpl();
        List<BankResource> bankResourcesList = bankResourceDao.getBankResourcesList();
        bankResourcesList.forEach(System.out::println);
        BankClientDao bankClientDao = new BankClientDaoImpl();
        List<BankClient> bankClients = bankClientDao.getBankClientList();
        bankClients.forEach(System.out::println);
    }
}