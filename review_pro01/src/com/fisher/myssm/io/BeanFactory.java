package com.fisher.myssm.io;

/**
 * IOC 接口
 * @author fisher
 * @version 1.0.1 2023/5/29 - 14:57
 */
public interface BeanFactory {

    //获取Bean对象
    Object getBean(String name);

}
