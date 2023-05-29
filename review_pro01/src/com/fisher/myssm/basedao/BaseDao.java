package com.fisher.myssm.basedao;

import com.fisher.myssm.basedao.exceptions.DaoException;
import com.fisher.myssm.utils.JdbcUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 数据访问对象
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:47
 */
public abstract class BaseDao<T> {

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    //T的Class对象
    private Class entityClass ;

    public BaseDao() {
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];

        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("BaseDao.Constructor 异常!");
        }
    }

    protected Connection getConnection(){
        return ConnectionUtils.getConnection();
    }

    protected void close(ResultSet result,PreparedStatement preparedStatement,Connection connection){

    }

    //给预处理对象设置参数
    private void setParam(PreparedStatement preparedStatement,Object... params) throws SQLException {
        if (params != null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1,params[i]);
            }
        }
    }

    //执行更新
    protected int executeUpdate(String sql,Object... params){
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase(Locale.ROOT).startsWith("INSERT");
        this.connection = getConnection();
        try{
            if (insertFlag){
                this.preparedStatement = this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            }else {
                this.preparedStatement = this.connection.prepareStatement(sql);
            }
            setParam(this.preparedStatement,params);
            int count = this.preparedStatement.executeUpdate();
            if(insertFlag){
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                if (this.resultSet.next()) {
                    return ((Long)this.resultSet.getLong(1)).intValue();
                }
            }
            return count;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("BaseDao.executeUpdate 异常!");
        }
    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj ,  String property , Object propertyValue) throws NoSuchFieldException,
            IllegalAccessException {
        Class clazz = obj.getClass();
        //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
        Field field = clazz.getDeclaredField(property);
        if(field!=null){
            field.setAccessible(true);
            field.set(obj,propertyValue);
        }
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql , Object... params) {
        connection = getConnection() ;
        try {
            preparedStatement = connection.prepareStatement(sql);
            setParam(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = resultSet.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);     //33    苹果      5
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new DaoException("BaseDao.executeComplexQuery 异常!");
        }
        return null ;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql , Object... params) {

        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            setParam(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = resultSet.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if (resultSet.next()) {
                @SuppressWarnings("unchecked")
                T entity = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = resultSet.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("BaseDao.load 异常!");
        }
        return null ;
    }


    //执行查询，返回List
    protected List<T> executeQuery(String sql , Object... params) {

        List<T> list = new ArrayList<>();

        connection = getConnection() ;
        try{
            preparedStatement = connection.prepareStatement(sql);
            setParam(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = resultSet.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while (resultSet.next()) {
                @SuppressWarnings("unchecked")
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = resultSet.getObject(i + 1)    ;     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("BaseDao.executeQuery 异常!");
        }
        return list ;
    }
}
