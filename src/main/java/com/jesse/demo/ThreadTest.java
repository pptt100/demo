package com.jesse.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jesse on 2018/5/2.
 */
public class ThreadTest {

    private static Map<String, Object> map1 = new HashMap<>();
    private static Map<String, Object> map2 = new Hashtable<>();
    private static Map<String, Object> map3 = new ConcurrentHashMap<>();
    private static Map<String, Object> map4 = Collections.synchronizedMap(new HashMap<String, Object>());
    private static Map<String, Object> map = map3;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (map.size() > 0) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            System.out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
                        }
                        map.clear();
                    }

                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<100; i++) {
                    map.put("key" + i, "value" + i);
                    try {
                        Thread.sleep((new Random().nextInt(10) + 1) * 1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
