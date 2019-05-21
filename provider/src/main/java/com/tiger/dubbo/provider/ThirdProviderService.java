package com.tiger.dubbo.provider;


import com.tiger.dubbo.api.DemoService;

public class ThirdProviderService implements DemoService {

    @Override
    public String sayHi(String name) {

        return "THIRD " + name;
    }
}
