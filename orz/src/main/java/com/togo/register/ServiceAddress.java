package com.togo.register;

import lombok.Data;

/**
 * @Author taiyn
 * @Description 服务地址
 * @Date 9:05 上午 2020/3/24
 **/
@Data
public class ServiceAddress {

    private String interfaceName;
    private String host;
    private int port;
}
