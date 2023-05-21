package com.fisher.fruit.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装Dao层数据库重复代码
 * TODO：
 *     封装两个方法：1.简化非DQL
 *                2.简化DQL
 * @author fisher
 * @version 1.0.1 2023/5/20 - 9:34
 */
public abstract class BaseDao {

    /**
     * 封装简化非DQL
     * @param sql  带占位符的SQL语句
     * @param params 占位符的值 传入占位符的值必须与SQL语句？位置相同
     * @return int 返回执行影响的行数
     */
    public int executeUpdate(String sql,Object... params) throws SQLException {
        //注册驱动并获取连接
        Connection connection = JdbcUtilsV2.getConnection();

        //创建Statement对象传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        for (int i = 0; i < params.length; i++)
            preparedStatement.setObject(i+1,params[i]);

        //执行SQL语句,获取结果
        int rows = preparedStatement.executeUpdate();

        //关闭资源
        preparedStatement.close();
        //是否回收连接？需要考虑是否为事务
        if (connection.getAutoCommit()){
            //没有开启事务，正常回收连接
            JdbcUtilsV2.freeConnection();
        }
        //若事务开启，交给业务层关闭连接

        return rows;
    }

    /**
     * 封装简化DQL
     * 返回值类型并不是List<Map>类型。map没有数据校验机制，map不支持反射操作
     *
     *    数据库数据 —> Java中的实体类
     *
     *    table：                ->     Java：
     *        t_user             ->       User类
     *           id              ->          id
     *           account         ->          account
     *           password        ->          password
     *           nickname        ->          nickname
     *
     *    表中一行数据 -> Java类的一个对象
     *    多行数据 -> List<Java实体类> list
     *
     * <T> 声明一个泛型，不确定类型
     *
     * public List<T> executeQuery(Class<T> class ,String sql,Object...params){}
     *
     *
     */
    /**
     * 将查询结果封装到实体类集合。
     *
     * @param clazz 要接值的实体类模板对象
     * @param sql 查询SQL语句，要求列名或别名等于实体类的属性名。
     * @param params 占位符的值，和？位置对应传递
     * @param <T> 声明的结果的泛型
     * @return list 查询到的实体类对象
     * @throws SQLException SQL异常2
     * @throws InstantiationException exception
     * @throws IllegalAccessException exception
     * @throws NoSuchFieldException exception
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException,
            InstantiationException, IllegalAccessException, NoSuchFieldException {
        //注册驱动并创建连接
        Connection connection = JdbcUtilsV2.getConnection();

        //创建Statement对象传入SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        if (params != null && params.length != 0)
            for (int i = 0; i < params.length; i++)
                preparedStatement.setObject(i + 1, params[i]);

        //执行SQL语句,获取结果
        ResultSet resultSet = preparedStatement.executeQuery();

        //解析结果集
        List<T> list = new ArrayList<>();

            //获取列信息
        ResultSetMetaData metaData = resultSet.getMetaData();
            //获取列数
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {

            T t = clazz.newInstance(); //调用类的无参构造函数实例化对象

            //一行数据表示一个T类型的对象

            for (int i = 1; i <= columnCount; i++) {
                //对象属性值
                Object value = resultSet.getObject(i);

                //对象属性名
                String columnLabel = metaData.getColumnLabel(i);

                //反射，给对象的属性值赋值
                Field field = clazz.getDeclaredField(columnLabel);
                field.setAccessible(true); //属性可以设置，打破private(私有)修饰
                /**
                 * field.set(Object object,Object value)
                 *
                 * @param object 要赋值的对象 （若属性为static，则object可以为空）
                 * @param value 具体的属性值
                 */
                field.set(t, value);
            }
            list.add(t);
        }
        //关闭资源
        resultSet.close();
        preparedStatement.close();

        if(connection.getAutoCommit()){
            //没有开启事务，正常回收连接
            JdbcUtilsV2.freeConnection();
        }
        //若事务开启，交给业务层关闭连接

        return list;
    }
}
