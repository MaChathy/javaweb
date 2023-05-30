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
public class BankClientDaoImpl extends BaseDao<BankClient> implements BankClientDao {
    @Override
    public List<BankClient> getBankClientList() {
        String sql = "SELECT * FROM bank_client;";
        List<BankClient> list = new ArrayList<>();
        list = super.executeQuery(sql);
        return list;
    }
}
