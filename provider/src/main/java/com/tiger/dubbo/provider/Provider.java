package com.tiger.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.DriverManager;

public class Provider {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
        context.start();
        System.in.read();
//        DriverManager
    }
}
