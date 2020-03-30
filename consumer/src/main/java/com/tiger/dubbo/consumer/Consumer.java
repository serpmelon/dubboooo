package com.tiger.dubbo.consumer;

import com.tiger.dubbo.api.DemoService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        DemoService service = (DemoService) context.getBean("demoService");

        RpcContext.getContext().setAttachment("tyn", "qwer");
        service.sayHi("aaaa");

        System.out.println("before ww " + RpcContext.getContext().getAttachment("tyn"));
    }

}
