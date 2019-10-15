package com.togo.service;

import com.tiger.dubbo.api.DemoService;
import com.togo.annotation.Service;

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
 * @date Created in 2019年10月12日 13:44
 * @since 1.0
 */
@Service
public class DemoServiceImpl implements DemoService {

    public String sayHi(String name) {
        return "hi " + name;
    }
}