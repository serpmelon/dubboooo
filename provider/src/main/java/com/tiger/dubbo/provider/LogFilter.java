package com.tiger.dubbo.provider;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.rpc.*;

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
 * @date Created in 2019年12月22日 21:29
 * @since 1.0
 */
//@Activate(group = CommonConstants.PROVIDER, order = 2)
public class LogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String id = RpcContext.getContext().getAttachment("tyn");
        System.out.println("id " + id);
        Result invoke = invoker.invoke(invocation);

        RpcContext.getServerContext().setAttachment("tyn", id);
        return invoke;
    }
}