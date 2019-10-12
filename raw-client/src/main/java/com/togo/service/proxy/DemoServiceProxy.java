package com.togo.service.proxy;

import com.tiger.dubbo.api.DemoService;
import com.togo.stub.RPCClient;

import java.io.IOException;

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
public class DemoServiceProxy implements DemoService {

    public String sayHi(String name){

        String msg = "com.tiger.dubbo.api.DemoService-sayHi-java.lang.String-" + name;
        try {
            return (String) RPCClient.sendMsg(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}