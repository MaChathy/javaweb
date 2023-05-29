package com.fisher.myssm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc 工具类
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:51
 */
public class JdbcUtils {

    //连接池对象
    private static DataSource dataSource = null;

    //线程本地变量，用来存放Connection对象
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //初始化连接对象
    static {
        try{
            Properties properties = new Properties();
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JdbcUtils(){}

    //对外提供连接的方法
    public static Connection getConnection(){
        //查看线程本地变量中是否存在Connection对象
        Connection connection = threadLocal.get();
        //若不存在，则创建并放入现成本地变量中
        if(connection == null){
            try {
                connection = dataSource.getConnection();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
            threadLocal.set(connection);
        }
        return connection;
    }

    //对外回收连接的方法
    public static void releaseConnection(){
        Connection connection = threadLocal.get();
        if (connection != null) {
            //清空线程本地变量
            threadLocal.remove();
            try {
                //回归事务状态
                connection.setAutoCommit(true);
                //关闭连接
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

}

