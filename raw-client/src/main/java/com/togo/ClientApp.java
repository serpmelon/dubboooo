package com.togo;

import com.tiger.dubbo.api.DemoService;
import com.togo.consumer.service.factory.ServiceFactory;


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
 * @date Created in 2019年10月12日 13:18
 * @since 1.0
 */
public class ClientApp {

    public static void main(String[] args) {

        DemoService service = ServiceFactory.createService(DemoService.class, "multi");
        int i = 5;
        while (i > -1) {
            i--;
            System.out.println(service.sayHi("haha"));
        }


    }
}