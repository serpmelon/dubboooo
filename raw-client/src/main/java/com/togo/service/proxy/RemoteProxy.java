package com.togo.service.proxy;

import com.tiger.dubbo.api.DemoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.Remote;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author taiyn
 * @version 1.0
 * @date Created in 2019年10月14日 21:22
 * @since 1.0
 */
public class RemoteProxy<T> implements InvocationHandler {

    private Class<T> klass;

    public RemoteProxy(Class<T> klass) {

        this.klass = klass;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // object的公用方法直接调用当前invoke对象的。
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
            // 针对接口的不同方法书写我们具体的实现
        } else if ("showName".equals(method.getName())) {
            System.out.println("张三");
        } else if ("saying".equals(method.getName())) {
            System.out.println("我叫张三");
        }

        return "asd";
    }

    public static void main(String[] args) {


    }
}