package com.togo.util;

/**
 * @Author taiyn
 * @Description two tuples
 * @Date 9:53 上午 2020/4/5
 **/
public class TwoTuples<T, K> {

    private T t;
    private K k;

    private TwoTuples(T t, K k) {

        this.t = t;
        this.k = k;
    }

    public static <T, K> TwoTuples<T, K> instance(T t, K k) {

        return new TwoTuples<>(t, k);
    }

    public T first() {
        return t;
    }

    public K second() {

        return k;
    }
}
