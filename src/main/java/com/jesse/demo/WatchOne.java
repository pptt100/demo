package com.jesse.demo;


import org.apache.log4j.Logger;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by Jesse on 2018/4/24.
 */
public class WatchOne {
    /**
     * log日志
     */
    private static final Logger logger = Logger.getLogger(WatchOne.class);

    private static final String CONNECTSTRING = "127.0.0.1:2181";

    private static final String PATH = "/zookeeper1";

    private static final int SESSION_TIMEOUT = 50*1000;

    private ZooKeeper zk = null;

    public ZooKeeper startZk() throws IOException{
        return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

    public void stopZk() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }

    public void createZnode(String path, String nodeValue) throws KeeperException, InterruptedException {
        zk.create(PATH, nodeValue.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public String getZNode(final String path) throws KeeperException, InterruptedException {
        byte[] byteArray = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

                try {
                    triggerValue(path);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new Stat());
        return new String(byteArray);
    }

    public String triggerValue(String path) throws KeeperException, InterruptedException {
        byte[] byteArray = zk.getData(path, false, new Stat());
        String retValue = new String(byteArray);
        System.out.println("**********triggerValue:" + retValue);
        return retValue;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        WatchOne watchOne = new WatchOne();
        ZooKeeper zooKeeper = watchOne.startZk();
        watchOne.setZk(zooKeeper);
        if (watchOne.getZk().exists(PATH, false) == null) {
            watchOne.createZnode(PATH, "bbb");
            System.out.println("*********>" + watchOne.getZNode(PATH));
            Thread.sleep(Long.MAX_VALUE);
        } else {
            System.out.println("i have znode");
        }
    }
}
