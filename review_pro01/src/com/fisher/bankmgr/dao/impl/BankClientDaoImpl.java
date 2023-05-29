package com.fisher.bankmgr.dao.impl;

import com.fisher.bankmgr.dao.BankClientDao;
import com.fisher.bankmgr.pojo.BankClient;
import com.fisher.myssm.basedao.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fisher
 * @version 1.0.1 2023/5/29 - 14:47
 */
public class BankClientDaoImpl extends BaseDao implements BankClientDao {
    @Override
    public List<BankClient> getBankClientList() {
        String sql = "SELECT * FROM bank_client;";
        List<BankClient> list = new ArrayList<>();
        try {
            list = super.executeQuery(BankClient.class,sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }
}
