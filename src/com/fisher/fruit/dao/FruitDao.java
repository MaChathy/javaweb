package com.fisher.fruit.dao;

import com.fisher.fruit.pojo.Fruit;

import java.util.List;

/**
 * fruit对应的数据库方法
 * @author fisher
 * @version 1.0.1 2023/5/20 - 20:27
 */
public interface FruitDao  {
    //查询库存列表
    List<Fruit> getFruitList();

    //根据fid获取特定的水果库存信息
    Fruit getFruitByFid(int fid);

    //根据水果id修改水果库存信息
    void updateFruit(Fruit fruit);

    //删除特定水果库存信息
    void delFruit(Integer fid);

    //添加水果库存信息
    void addFruit(Fruit fruit);

    /*
    //新增库存
    boolean addFruit(Fruit fruit);

    //修改库存
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);
     */
}
