package com.togo.register;

import com.togo.util.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

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

    class ZooUtil implements Watcher {

        private ZooKeeper zooKeeper;

        public ZooUtil(String host, int timeout) {
            try {
                zooKeeper = new ZooKeeper(host, timeout, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 创建节点
         *
         * @param node
         * @param data
         */
        public void createNode(String node, byte[] data) {
            try {
                zooKeeper.create(node, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 删除节点
         *
         * @param node
         * @param version
         */
        public void delete(String node, int version) {

            try {
                zooKeeper.delete(node, version);
            } catch (InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }

        /**
         * 更新节点
         *
         * @param node
         * @param data
         * @param version
         */
        public void update(String node, byte[] data, int version) {

            try {
                zooKeeper.setData(node, data, version);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 查询节点
         *
         * @param node
         * @return
         */
        public String select(String node) {

            try {
                byte[] data = zooKeeper.getData(node, this, null);
                return new String(data, StandardCharsets.UTF_8);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 版本信息
         *
         * @param node
         * @return
         */
        public int version(String node) {

            try {
                return zooKeeper.exists(node, true).getVersion();
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }

            return -1;
        }

        /**
         * 检查是否存在
         *
         * @param node
         * @param watch
         * @return
         */
        public boolean exists(String node, boolean watch) {

            try {
                Stat stat = zooKeeper.exists("/fff", watch);
                return stat != null;
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();

                throw new RuntimeException();
            }
        }

        /**
         * 监听事件
         *
         * @param event
         */
        @Override
        public void process(WatchedEvent event) {

            if (event.getType() == Watcher.Event.EventType.NodeDataChanged) { // 节点变换
                System.out.println("changed");
                System.out.println(select("/java"));
            }
        }
    }
}
