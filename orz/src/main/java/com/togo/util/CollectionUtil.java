package com.togo.util;

import java.util.Collection;

/**
 * @Author taiyn
 * @Description TODO
 * @Date 9:49 下午 2020/4/11
 **/
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {

        return collection == null || collection.isEmpty();
    }
}
