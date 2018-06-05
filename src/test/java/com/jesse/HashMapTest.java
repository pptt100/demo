package com.jesse;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Jesse on 2018/5/31.
 */
public class HashMapTest {

    @Test
    public void test01() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("333", "444");

        String s = hashMap.get("333");
        System.out.println(s);
    }

}
