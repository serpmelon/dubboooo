package com.togo.register;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author taiyn
 * @Description 注册中心
 * @Date 9:58 下午 2020/3/23
 **/
public enum Register {

    INSTANCE;

    private String root = "orz";
    private ZooKeeper zooKeeper;
    private OrzRegisterWatcher watcher;
    private String host = "127.0.0.1:2181";
    private int timeout;

    Register() {
        watcher = new OrzRegisterWatcher();
        timeout = 1000;
        try {
            zooKeeper = new ZooKeeper(host, timeout, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn() {

    }

    public void scan() {

    }

}
