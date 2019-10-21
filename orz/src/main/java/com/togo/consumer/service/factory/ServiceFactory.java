package com.togo.consumer.service.factory;

import com.togo.consumer.service.proxy.RemoteProxy;

import java.lang.reflect.Proxy;

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
 * @date Created in 2019年10月12日 13:20
 * @since 1.0
 */
public class ServiceFactory {

    public static <T> T createService(Class<T> klass, String alias) {

        if (klass == null)
            return null;

        RemoteProxy<T> rp = new RemoteProxy<T>(klass, alias);
        Object subject = Proxy.newProxyInstance(rp.getClass().getClassLoader(), new Class[]{klass}, rp);

        return (T) subject;
    }
}