package com.fisher.bankmgr.service.impl;

import com.fisher.bankmgr.dao.BankClientDao;
import com.fisher.bankmgr.pojo.BankClient;
import com.fisher.bankmgr.service.BankMgrService;

import java.util.List;

/**
 * 业务层实现类
 * @author fisher
 * @version 1.0.1 2023/5/29 - 15:03
 */
public class BankMgrServiceImpl implements BankMgrService {

    private BankClientDao bankClientDao = null;

    @Override
    public List<BankClient> getBankClientList() {
        return bankClientDao.getBankClientList();
    }
}
