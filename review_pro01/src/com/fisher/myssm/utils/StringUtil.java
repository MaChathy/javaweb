package com.fisher.myssm.utils;

/**
 * 字符串工具类
 *  isEmpty(String str)
 *  isNotEmpty(String str)
 * @author fisher
 * @version 1.0.1 2023/5/28 - 22:46
 */
public class StringUtil {

    //判断字符串是否为null或者为“”,若不是返回false;否则返回true
    public static boolean isEmpty(String str) {
        return "".equals(str) || str == null ;
    }

    //判断字符串是否不为null或者为“”,若是返回false;否则返回true
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
