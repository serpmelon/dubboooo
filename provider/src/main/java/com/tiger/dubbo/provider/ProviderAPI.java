package com.tiger.dubbo.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.tiger.dubbo.api.DemoService;

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
 * @date Created in 2019年05月21日 11:53
 * @since 1.0
 */
public class ProviderAPI {

    public static void main(String[] args) throws Exception {

        DemoService service = new ThirdProviderService();

        ApplicationConfig appConfig = new ApplicationConfig();
        appConfig.setName("third");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(9024);
        protocolConfig.setThreads(100);

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(appConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(service);

        serviceConfig.export();

        System.in.read();
    }
}
