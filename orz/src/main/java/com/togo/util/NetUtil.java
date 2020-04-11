package com.togo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author taiyn
 * @Description TODO
 * @Date 10:31 下午 2020/4/11
 **/
public class NetUtil {

    public static String localIP() {

        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("can not find local ip");
    }
}
