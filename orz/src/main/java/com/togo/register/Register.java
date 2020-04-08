package com.togo.register;

import com.togo.util.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author taiyn
 * @Description 注册中心
 * @Date 9:58 下午 2020/3/23
 **/
@Slf4j
public class Register {

    private String root = "/orz";
    private ZooKeeper zooKeeper;
    private OrzRegisterWatcher watcher;
    private int timeout = 1000;

    private String host;
    private int port;

    private static Register register;

    private Register() {
    }

    // TODO taiyn 2020/3/30 重写单例模式
    public static Register instance() {

        if (register != null)
            return register;

        register = new Register();
        return register;
    }

    public void init() {

        ConfigUtil configUtil = ConfigUtil.instance();
        host = configUtil.read("register.host");
        port = configUtil.readInt("register.port");
        watcher = new OrzRegisterWatcher();
        try {
            log.info("register init...");
            zooKeeper = new ZooKeeper(host + ":" + port, timeout, watcher);
            log.info("register init end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void signIn() {

    }

    public void scan() {

    }

    public String select(String node) {

        try {
            byte[] data = zooKeeper.getData(node, null, null);
            return new String(data, StandardCharsets.UTF_8);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
