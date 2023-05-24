package com.fisher.fruit.dao.impl;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.myssm.basedao.BaseDao;
import com.fisher.fruit.pojo.Fruit;

import java.util.List;

/**
 *
 * @author fisher
 * @version 1.0.1 2023/5/20 - 20:31
 */
public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruit getFruitByFid(int fid) {
        String sql = "select * from t_fruit where fid = ?";
        return super.load(sql,fid);
    }

    /*
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
     */
}
