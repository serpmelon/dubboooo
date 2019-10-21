package com.togo.consumer.service.proxy;

import com.alibaba.fastjson.JSONObject;
import com.togo.consumer.stub.RPCClient;
import com.togo.message.Message;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
    private String alias;

    public RemoteProxy(Class<T> klass, String alias) {

        this.klass = klass;
        this.alias = alias;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Message message = new Message();
        message.setKlassName(klass.getName());
        message.setAlias(alias);
        message.setMethodName(method.getName());
        message.setParameterKlassNameArrays(method.getParameterTypes());
        message.setParameterArrays(args);

        return RPCClient.sendMsg(JSONObject.toJSONString(message));
    }
}