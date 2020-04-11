package com.togo.register;

import com.togo.annotation.scan.Key;
import com.togo.context.ServiceContext;
import com.togo.provider.stub.RPCServer;
import com.togo.util.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @Author taiyn
 * @Description 注册中心
 * @Date 9:58 下午 2020/3/23
 **/
@Slf4j
public class Register {

    private String root = "/orz";
    private ZooKeeper zooKeeper;
    private int timeout = 1000;

    private String host;
    private int port;
    private OrzZooKeeper orzZooKeeper;

    private static Register register = new Register();
    ;

    private Register() {
    }

    public static Register instance() {

        return register;
    }

    public void init() {

        ConfigUtil configUtil = ConfigUtil.instance();
        host = configUtil.read("register.host");
        port = configUtil.readInt("register.port");
        log.info("register init...");
        orzZooKeeper = new OrzZooKeeper(host + ":" + port, timeout);
        log.info("register init end");
    }

    public void signIn() {

        Map<Key, String> implMap = ServiceContext.INSTANCE.getAllServiceImpls();
        if (orzZooKeeper.notExists(root, false)) {
            orzZooKeeper.createNode(root);
        }

        implMap.forEach((k, v) -> {

            orzZooKeeper.createDeepNode("", k.orzName(), ip() + ":" + RPCServer.port);
        });
    }

    public void scan() {

    }

    private String ip() {

        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("can not find local ip");
    }

    class OrzZooKeeper implements Watcher {

        private ZooKeeper zooKeeper;

        OrzZooKeeper(String host, int timeout) {
            try {
                zooKeeper = new ZooKeeper(host, timeout, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * create node
         *
         * @param node just node
         * @param data just data
         */
        private void createNode(String node, byte[] data) {
            try {
                zooKeeper.create(node, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                log.info("create node: [{}]", node);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void createNode(String node, String data) {
            createNode(node, data.getBytes());
        }

        private void createNode(String node) {
            createNode(node, "");
        }

        /**
         * create multiple directory and set <code>data</code> to the deepest node,
         * create with <code>null</code> data if any directory do not exist
         *
         * @param data  data
         * @param nodes multiple node
         */
        private void createDeepNode(String data, String... nodes) {

            if (nodes.length == 0)
                return;

            StringBuilder dir = new StringBuilder(root);
            for (int i = 0; i < nodes.length - 1; i++) {
                dir.append("/");
                dir.append(nodes[i]);
                existsOrCreate(dir.toString(), true);
            }

            dir.append("/");
            dir.append(nodes[nodes.length - 1]);
            createNode(dir.toString(), data);
        }


        /**
         * 删除节点
         *
         * @param node
         * @param version
         */
        private void delete(String node, int version) {

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
        private void update(String node, byte[] data, int version) {

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
        private String select(String node) {

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
        private int version(String node) {

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
        private boolean exists(String node, boolean watch) {

            try {
                Stat stat = zooKeeper.exists(node, watch);
                return stat != null;
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();

                throw new RuntimeException();
            }
        }

        private boolean notExists(String node, boolean watch) {

            return !exists(node, watch);
        }

        /**
         * create node if the node do not exist. the data of node is nothing.
         *
         * @param node
         * @param watch
         * @return
         */
        private void existsOrCreate(String node, boolean watch) {

            if (!exists(node, watch))
                createNode(node, "");
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
