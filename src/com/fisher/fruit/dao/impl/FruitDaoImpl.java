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
    public int getFruitCount(String keyword) {
        String sql = "select * from t_fruit where fname like ? or remark like ? ";
        return super.executeQuery(sql,"%"+keyword+"%","%"+keyword+"%").size();
    }

    @Override
    public List<Fruit> getFruitList(String keyword,Integer pageNo) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ? , 5";
        return super.executeQuery(sql,"%"+keyword+"%","%"+keyword+"%",(pageNo-1)*5);
    }

    @Override
    public Fruit getFruitByFid(int fid) {
        String sql = "select * from t_fruit where fid = ?";
        return super.load(sql,fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?,price=?," +
                " fcount =? ,remark=? where fid =? " ;
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        String sql = "delete from t_fruit where fid = ?";
        super.executeUpdate(sql,fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }
}
