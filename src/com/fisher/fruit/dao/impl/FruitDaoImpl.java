package com.fisher.fruit.dao.impl;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.base.BaseDao;
import com.fisher.fruit.pojo.Fruit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fisher
 * @version 1.0.1 2023/5/20 - 20:31
 */
class FruitDao1 extends BaseDao<Fruit>{

    public List<Fruit> getAllFruit(){
        String sql = "SELECT * FROM t_fruit";
        List<Fruit> fruits = null;
        try {
            fruits = this.executeQueryA(Fruit.class,sql);
        } catch (SQLException | NoSuchFieldException |
                IllegalAccessException | InstantiationException
                throwable) {
            throwable.printStackTrace();
        }
        return fruits;
    }

    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = 0;
        try {
            count = super.executeUpdateA(sql,fruit.getFname(),
                    fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count > 0;
    }

    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount =? where fid =? " ;
        boolean row = false;
        try {
            row = super.executeUpdateA(sql,fruit.getFcount(),
                    fruit.getFid()) > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }

    public Fruit getFruitByFname(String fname) {
        String sql = "SELECT * FROM t_fruit WHERE fname like ?";
        List<Fruit> list = new ArrayList<Fruit>();
        try {
            list = this.executeQueryA(Fruit.class,sql,fname);
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }
        return list.get(0);
    }

    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like? " ;
        boolean row = false;
        try {
            row = super.executeUpdateA(sql,fname)>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }


}
public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int count = super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
        return count>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ? " ;
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid())>0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from t_fruit where fname like ? ",fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ? " ;
        return super.executeUpdate(sql,fname)>0;
    }
}
