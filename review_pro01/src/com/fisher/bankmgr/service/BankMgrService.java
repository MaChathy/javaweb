package com.fisher.bankmgr.service;

import com.fisher.bankmgr.pojo.BankClient;

import java.util.List;

/**
 * 业务层
 * @author fisher
 * @version 1.0.1 2023/5/29 - 15:01
 */
public interface BankMgrService {

    //获取所有用户信息
    List<BankClient> getBankClientList();

}
