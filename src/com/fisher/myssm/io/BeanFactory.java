package com.fisher.myssm.io;

/**
 * Bean : 描述Java的软件组件模型
 * @author fisher
 * @version 1.0.1 2023/5/28 - 14:48
 */
public interface BeanFactory {

    //获取Bean对象
    Object getBean(String id);

}
