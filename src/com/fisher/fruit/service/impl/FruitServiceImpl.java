package com.fisher.fruit.service.impl;

import com.fisher.fruit.dao.FruitDao;
import com.fisher.fruit.pojo.Fruit;
import com.fisher.fruit.service.FruitService;

import java.util.List;

/**
 * 业务层功能实现类
 * @author fisher
 * @version 1.0.1 2023/5/28 - 12:50
 */
public class FruitServiceImpl implements FruitService {

    private FruitDao fruitDao = null;

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
    public void updateFruit(Fruit newFruit) {
        fruitDao.updateFruit(newFruit);
    }

    @Override
    public Integer getPageCount(String keyword) {
        return (fruitDao.getFruitCount(keyword)+4)/5;
    }
}
