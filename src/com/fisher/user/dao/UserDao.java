package com.fisher.user.dao;

import com.fisher.user.pojo.User;

import java.util.List;

/**
 * 用户类的数据库操作接口
 *
 * @author fisher
 * @version 1.0.1 2023/5/24 - 10:39
 */
public interface UserDao {

    //查询所有用户，返回用户列表
    List<User> getUserList();

}
