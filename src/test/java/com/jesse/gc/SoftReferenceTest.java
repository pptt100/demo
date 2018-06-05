package com.jesse.gc;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse on 2018/5/31.
 */
public class SoftReferenceTest {

    private static List<Byte[]> list = new ArrayList<Byte[]>();;
    private static SoftReference<SoftReferenceTest> sr;

    public static void main(String[] args) {
        // 软引用
        // 当内存溢出的时候，应用的对象会被 回收
        sr = new SoftReference<SoftReferenceTest>(new SoftReferenceTest());
        System.out.println("sr : " + sr.get());

        sr= null;
        System.gc();
        // 产生MMO后可以看到finalize的执行，即“软引用，当内存溢出的时候，应用的对象会被 回收”
//        Byte[] bytes = null;
//        for (int i = 0; i < 50000; i++) {
//            bytes = new Byte[1024 * 1024];
//            list.add(bytes);
//        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("SoftReferenceTest ... finalize ... exec...");
        System.out.println("sr : " + sr.get());
    }

}
