package com.fisher.bankmgr.dao;

import com.fisher.bankmgr.pojo.BankResource;

import java.util.List;

/**
 * 数据访问对象
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:39
 */
public interface BankResourceDao {
    //显示银行资源列表
    List<BankResource> getBankResourcesList();
    //修改指定资源信息
    void updateBankResource(String res_name,Integer res_count);
}
