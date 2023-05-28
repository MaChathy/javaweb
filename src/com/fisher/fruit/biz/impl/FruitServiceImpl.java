package com.fisher.fruit.biz.impl;

import com.fisher.fruit.biz.FruitService;
import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.dao.impl.FruitDaoImpl;
import com.fisher.fruit.pojo.Fruit;

import java.util.List;

/**
 * 业务层功能实现类
 * @author fisher
 * @version 1.0.1 2023/5/28 - 12:50
 */
public class FruitServiceImpl implements FruitService {

    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDao.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDao.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFId(Integer fid) {
        return fruitDao.getFruitByFid(fid);
    }

    @Override
    public void deleteFruit(Integer fid) {
        fruitDao.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        return (fruitDao.getFruitCount(keyword)+4)/5;
    }
}
