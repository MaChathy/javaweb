package com.fisher.fruit.service;

import com.fisher.fruit.pojo.Fruit;

import java.util.List;

/**
 * 新增业务层
 * @author fisher
 * @version 1.0.1 2023/5/28 - 12:45
 */
public interface FruitService {

    //获取指定页面的库存列表信息
    List<Fruit> getFruitList(String keyword,Integer pageNo);

    //添加库存记录信息
    void addFruit(Fruit fruit);

    //根据fid查看指定库存记录
    Fruit getFruitByFId(Integer fid);

    //删除特定库存记录
    void deleteFruit(Integer fid);

    //修改特定库存记录
    void updateFruit(Fruit newFruit);

    //获取总页数
    Integer getPageCount(String keyword);
}
