package com.fisher.fruit.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JdbcUtilsV1 进阶
 * TODO:
 *      利用ThreadLocal（线程本地）变量，存储连接信息。
 *      确保多个方法可以使用同一个Connection 对象
 *      优势：事务操作时，Service和Dao方法属于同一个线程，不用传递Connection对象参数
 *           都可以调用getConnection()自动获取相同的连接池。
 * @author fisher
 * @version 1.0.1 2023/5/19 - 22:56
 */
public class JdbcUtilsV2 {
    //连接池对象
    private static DataSource dataSource =  null;
    //线程本地对象
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            //初始化连接对象
            Properties properties = new Properties();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("druid.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private JdbcUtilsV2(){}

    //对外提供连接的方法
    public static Connection getConnection() throws SQLException {
        //查看线程本地对象中是否存在连接
        Connection connection = threadLocal.get();

        //若不存在线程本地变量，则从连接池中获取连接
        if (connection == null){
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    //回收外部传入连接的方法
    public static void freeConnection() throws SQLException {
        Connection connection = threadLocal.get();
        //清空线程本地变量数据
        if (connection != null) {
            threadLocal.remove();
            //回归事务状态
            connection.setAutoCommit(true);
            //回收连接到连接池
            connection.close();
        }
    }
}
