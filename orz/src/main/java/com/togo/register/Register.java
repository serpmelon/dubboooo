package com.togo.register;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author taiyn
 * @Description 注册中心
 * @Date 9:58 下午 2020/3/23
 **/
public class Register {

    private String root = "/orz";
    private ZooKeeper zooKeeper;
    private OrzRegisterWatcher watcher;
    private String zkAddress = "127.0.0.1:2181";
    private int timeout;

    private String host;
    private int port;

    private static Register register;

    private Register(String host, int port) {
        this.host = host;
        this.port = port;

        watcher = new OrzRegisterWatcher();
        timeout = 1000;
        try {
            zooKeeper = new ZooKeeper(zkAddress, timeout, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO taiyn 2020/3/30 重写单例模式
    public static Register instance(String host, int port) {

        if (register != null)
            return register;

        register = new Register(host, port);
        return register;
    }

    public void signIn() {

    }

    public void scan() {

    }
}
