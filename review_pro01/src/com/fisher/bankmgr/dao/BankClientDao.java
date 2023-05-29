package com.fisher.bankmgr.dao;

import com.fisher.bankmgr.pojo.BankClient;

import java.util.List;

/**
 * 银行客户数据访问对象接口
 * @author fisher
 * @version 1.0.1 2023/5/29 - 14:45
 */
public interface BankClientDao {

    //获取所有客户表
    List<BankClient> getBankClientList();

}
