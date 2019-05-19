package com.tiger.dubbo.provider;

import com.tiger.dubbo.api.DemoService;

public class ProviderService implements DemoService {

    @Override
    public String sayHi(String name) {

        return "provider " + name;
    }
}
