package com.togo;

import java.net.URL;

/**
 * @Author taiyn
 * @Description TODO
 * @Date 9:47 下午 2020/4/4
 **/
public class Path {

    public static void main(String[] args) {


//        String a = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader1 = Path.class.getClassLoader();
        classLoader.getParent();
//        URL url = classLoader.getResource("/");
//        URL url1 = classLoader1.getResource("/");
        URL url2 = classLoader1.getResource("com");
        URL url3 = classLoader1.getResource("com/togo");
        URL url4 = classLoader.getResource("com/togo");
//        URL url5 = new URL("com/togo");
        URL url6 = Path.class.getResource("/com/togo");

//        String b = Thread.currentThread().getClass().getClassLoader().getResource("").getPath();
//        System.out.println(a);
//        System.out.println(b);
        System.out.println(url2);
        System.out.println(url3);
        System.out.println(url4);
        System.out.println(url6);
//        try {
//            RPCServer.init();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
