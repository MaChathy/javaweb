package com.fisher.bankmgr.dao.impl;

import com.fisher.bankmgr.dao.BankResourceDao;
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
public class BankResourceDaoImpl extends BaseDao implements BankResourceDao {

    @Override
    public List<BankResource> getBankResourcesList() {
        String sql = "SELECT * FROM bank_res;";
        List<BankResource> list = new ArrayList<>();
        try {
            list = super.executeQuery(BankResource.class,sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateBankResource(String res_name,Integer res_count) {
        String sql = "UPDATE bank_res set res_count = ? where res_name = ?;";
        try {
            super.executeUpdate(sql, res_count, res_name);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
