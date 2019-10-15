package com.togo.service.proxy;

import com.alibaba.fastjson.JSONObject;
import com.tiger.dubbo.api.DemoService;
import com.togo.protocol.message.Message;
import com.togo.stub.RPCClient;

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

        Message message = new Message();
        message.setKlassName(klass.getName());
        message.setMethodName(method.getName());
        message.setParameterKlassNameArrays(method.getParameterTypes());
        message.setParameterArrays(args);

        return RPCClient.sendMsg(JSONObject.toJSONString(message));
    }
}