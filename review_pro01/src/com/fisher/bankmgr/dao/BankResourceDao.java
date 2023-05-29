package com.fisher.bankmgr.dao;

import com.fisher.bankmgr.pojo.BankResource;

import java.util.List;

/**
 * 数据访问对象
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:39
 */
public interface BankResourceDao {
    //
    List<BankResource> getBankResourcesList();

}
