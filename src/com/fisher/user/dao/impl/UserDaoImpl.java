package com.fisher.user.dao.impl;

import com.fisher.myssm.basedao.BaseDao;
import com.fisher.user.dao.UserDao;
import com.fisher.user.pojo.User;

import java.util.List;

/**
 * 用户类操作数据库实现。
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 10:44
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public List<User> getUserList() {
        String sql = "SELECT * FROM t_user";
        return this.executeQuery(sql);
    }
}
