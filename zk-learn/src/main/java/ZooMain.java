import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @Author taiyn
 * @Description TODO
 * @Date 7:04 下午 2020/3/24
 **/
public class ZooMain implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    public ZooMain(String host, int timeout) {
        try {
            zooKeeper = new ZooKeeper(host, timeout, this);
//            zooKeeper.getData()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createNode(String node, byte[] data) {
        try {
            zooKeeper.create(node, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(String node, int version) {

        try {
            zooKeeper.delete(node, version);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public void update(String node, byte[] data, int version) {

        try {
            zooKeeper.setData(node, data, version);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String select(String node) {

        try {
            byte[] data = zooKeeper.getData(node, this, null);
            return new String(data, StandardCharsets.UTF_8);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int version(String node) {

        try {
            return zooKeeper.exists(node, true).getVersion();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean exists(String node, boolean watch) {

        try {
            Stat stat = zooKeeper.exists("/fff", watch);
            return stat != null;
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();

            throw new RuntimeException();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1:2181";
        ZooMain zooMain = new ZooMain(host, 15000);

//        String node = "/java";
//        if (zooMain.exists(node, true))
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
        zooMain.select("/java");
        while (true) {

            Thread.sleep(3000);

        }
    }

    @Override
    public void process(WatchedEvent event) {

        if (event.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("changed");
            System.out.println(select("/java"));
        }
    }
}
