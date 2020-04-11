package com.togo.service;

import com.tiger.dubbo.api.DemoService;
import com.togo.annotation.OrzService;

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
 * @date Created in 2019年10月15日 17:23
 * @since 1.0
 */
@OrzService(name = "multi")
public class MultiDemoServiceImpl implements DemoService {

    @Override
    public String sayHi(String name) {
        return "multi " + name;
    }
}