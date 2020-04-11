import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author taiyn
 * @Description TODO
 * @Date 7:04 下午 2020/3/24
 **/
public class ZooMain implements Watcher {

    private static ZooKeeper zooKeeper;

    public ZooMain(String host, int timeout) {
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

    public List<String> selectChildren(String node) {

        try {
            return zooKeeper.getChildren(node, this);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 监听事件
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {

        if (event.getType() == Event.EventType.NodeDataChanged) { // 节点变换
            System.out.println("changed");
            System.out.println(select("/java"));
        }
        if (event.getType() == Event.EventType.NodeCreated) {
            System.out.println("create");
            select("/");
        }
        if (event.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("children");
            System.out.println(event.getPath());
            select("/");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1:2181";
        ZooMain zooMain = new ZooMain(host, 15000);

        String node = "/javas";
        if (zooMain.exists(node, true))
            System.out.println(123);
        else
            System.out.println(456);
        System.out.println(zooMain.selectChildren(node));
//            zooMain.createNode(node, "wahaha".getBytes());
//        System.out.println(zooMain.select(node));
//        int version = zooMain.version(node);
//        System.out.println("v " + version);
//        zooMain.update(node, "heiheihei".getBytes(), version);
//        System.out.println(zooMain.select(node));
//        version = zooMain.version(node);
//        System.out.println("v " + version);
//        zooMain.delete(node, version);
//        System.out.println(zooMain.select(node));
        zooMain.select("/");
        while (true) {

            Thread.sleep(3000);
        }
    }
}
