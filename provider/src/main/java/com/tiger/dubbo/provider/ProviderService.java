package com.tiger.dubbo.provider;

import com.tiger.dubbo.api.DemoService;
import org.apache.dubbo.rpc.RpcContext;

public class ProviderService implements DemoService {

    @Override
    public String sayHi(String name) {

        System.out.println(RpcContext.getContext().getAttachment("tyn"));
        return "provider " + name;
    }
}
