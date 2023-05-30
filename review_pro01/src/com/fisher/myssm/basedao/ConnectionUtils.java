package com.fisher.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This program demonstrates webfisher
 *
 * @author fisher
 * @version 1.0.1 2023/5/30 - 22:15
 */
public class ConnectionUtils {

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/bankdb?" +
            "useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "547547" ;

    //创建连接
    private static Connection createConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PWD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //获取连接
    public static Connection getConnection(){
        Connection connection = connectionThreadLocal.get();
        if (connection == null){
            connection = createConnection();
            connectionThreadLocal.set(connection);
        }
        return connectionThreadLocal.get();
    }
    //回收连接
    public static void releaseConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            return;
        }
        if (!connection.isClosed()) {
            connection.close();
            connectionThreadLocal.set(null);
        }
    }
}
