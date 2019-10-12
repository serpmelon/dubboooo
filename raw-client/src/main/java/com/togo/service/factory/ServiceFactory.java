package com.togo.service.factory;

import com.tiger.dubbo.api.DemoService;
import com.togo.service.proxy.DemoServiceProxy;

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


    public static DemoService createService(String name) {

        if ("demo".equals(name))
            return new DemoServiceProxy();
        return new DemoServiceProxy();
    }
}