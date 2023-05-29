package com.fisher.bankmgr.dao.impl;

import com.fisher.bankmgr.dao.BankResourceDao;
import com.fisher.bankmgr.pojo.BankClient;
import com.fisher.bankmgr.pojo.BankResource;
import com.fisher.myssm.basedao.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 银行资源数据访问对象的实现类
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:41
 */
public class BankResourceDaoImpl extends BaseDao<BankResource> implements BankResourceDao {

    @Override
    public List<BankResource> getBankResourcesList() {
        String sql = "SELECT * FROM bank_res;";
        List<BankResource> list = new ArrayList<>();
        list = super.executeQuery(sql);
        return list;
    }

    @Override
    public void updateBankResource(String res_name,Integer res_count) {
        String sql = "UPDATE bank_res set res_count = ? where res_name = ?;";
        super.executeUpdate(sql, res_count, res_name);
    }
}
