import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author taiyn
 * @Description TODO
 * @Date 7:04 下午 2020/3/24
 **/
public class ZooMain implements Watcher {

    ZooKeeper zooKeeper;
    public ZooMain(String host, int timeout) {
        try {
            this.zooKeeper = new ZooKeeper(host, timeout, this);
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void update(String node, byte[] data, int version) {

        try {
            zooKeeper.setData(node, data, version);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public int version(String node) {

        try {
            return zooKeeper.exists(node, true).getVersion();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void main(String[] args) {

        String host = "127.0.0.1:2181";
        ZooMain zooMain = new ZooMain(host, 1000);

        String node = "/java/src";
        zooMain.createNode(node, "wahaha".getBytes());
        System.out.println(zooMain.select(node));
        int version = zooMain.version(node);
        System.out.println("v " + version);
        zooMain.update(node, "heiheihei".getBytes(), version);
        System.out.println(zooMain.select(node));
        version = zooMain.version(node);
        System.out.println("v " + version);
        zooMain.delete(node, version);
        System.out.println(zooMain.select(node));
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
