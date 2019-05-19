package com.tiger.dubbo.provider;

import com.tiger.dubbo.api.DemoService;

public class SecondProviderService implements DemoService {
    @Override
    public String sayHi(String name) {

        return "second " + name;
    }
}
