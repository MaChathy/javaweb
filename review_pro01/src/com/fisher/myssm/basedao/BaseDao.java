package com.fisher.myssm.basedao;

import com.fisher.myssm.utils.JdbcUtils;
import com.mysql.cj.util.StringUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 数据访问对象
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:47
 */
public abstract class BaseDao {

    //非DQL
    public int executeUpdate(String sql,Object...params ) throws SQLException {
        Connection connection = JdbcUtils.getConnection();

        //创建Statement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //占位符赋值
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        //执行更新语句
        int rows = preparedStatement.executeUpdate();
        //是否开启事务,若开启事务，则不回收连接，交给事务处理
        if (connection.getAutoCommit()){
            //若没有开启事务，则回收连接
            JdbcUtils.releaseConnection();
        }
        return rows;
    }
    //DQL
    public <T> List<T> executeQuery(Class<T> clazz,String sql,Object...params) throws SQLException{
        Connection connection = JdbcUtils.getConnection();
        //创建Statement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //占位符赋值
        if(params != null && params.length != 0 )
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        //执行查询语句
        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getObject("res_name")+"\t"+resultSet.getObject("res_count"));
//        }
        //解析结果集
        List<T> list = new ArrayList<>();
        //获取列信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        //获取列数
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            try {
                //调用类的无参构造函数实例化对象
                T t = clazz.newInstance();
                //一行数据表示一个T类型的对象
                for (int i = 1; i <= columnCount; i++) {
                    //对象属性值
                    Object value = resultSet.getObject(i);
                    //对象属性名
                    String columnLabel = metaData.getColumnLabel(i);

                    //利用反射给对象的属性值赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    if (field.getType().getName().equals("java.long.Integer")) {
                        field.set(t,Integer.parseInt(value.toString()));
                    }else {
                        field.set(t,value);
                    }
                }
                list.add(t);
            } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        //关闭资源
        resultSet.close();
        preparedStatement.close();

        //是否开启事务,若开启事务，则不回收连接，交给事务处理
        if (connection.getAutoCommit()){
            //若没有开启事务，则回收连接
            JdbcUtils.releaseConnection();
        }
        return list;
    }
}
