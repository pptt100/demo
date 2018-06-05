package com.jesse.proxy;

/**
 * Created by Jesse on 2018/5/30.
 */
public class TestDynamicProxy {

    public static void main(String[] args) {
        EazyImpl eazy = new EazyImpl();
        eazy.add(10, 5);
        eazy.sub(10, 5);
        eazy.mul(10, 5);
        eazy.div(10, 5);
        System.out.println("------------------");
        DynamicProxy dynamicProxy = new DynamicProxy(eazy);
        MathCalculator  proxy = (MathCalculator)dynamicProxy.getProxy();
        proxy.add(20, 5);
        proxy.sub(20, 5);
        proxy.mul(20, 5);
        proxy.div(20, 5);
    }

}
