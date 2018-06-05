package com.jesse.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by Jesse on 2018/5/30.
 */
public class DynamicProxy {

    private Object targetObj;

    public DynamicProxy(Object targetObj) {
        this.targetObj = targetObj;
    }

    //动态创建代理对象
    public Object getProxy() {
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    System.out.println("[日志][" + method.getName() + "方法开始][参数值:" + (args == null ? "" : Arrays.asList(args)) + "]");
                    //执行目标
                    result = method.invoke(targetObj, args);
                    System.out.println("[日志][" + method.getName() + "方法结束][参数值:" + (args == null ? "" : Arrays.asList(args)) + "]");
                } catch (Exception e) {
                    //扩展日志
                    System.out.println("[日志][" + method.getName() + "方法异常][异常消息:" + e.getMessage());
                    e.printStackTrace();
                } finally {
                    System.out.println("[日志][" + method.getName() + "方法最终结束了]");
                }
                return result;
            }
        });
    }

}
