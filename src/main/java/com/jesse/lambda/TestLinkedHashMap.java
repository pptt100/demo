package com.jesse.lambda;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Jesse on 2018/5/30.
 */
public class TestLinkedHashMap {

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "西游记");
        map.put("2", "三国演义");
        map.put("3", "水浒传");
        map.put("4", "红楼梦");

        map.forEach((k, v) -> print(k, v));
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                print(s, s2);
            }
        });
    }

    public static void print(String k, String v) {
        System.out.println("k:" + k + ",v:" + v);
    }


}
