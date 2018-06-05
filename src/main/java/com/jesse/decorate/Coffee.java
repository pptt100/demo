package com.jesse.decorate;

/**
 * Created by Jesse on 2018/5/30.
 */
public class Coffee implements Drink {
    @Override
    public void taste() {
        System.out.println("咖啡好苦，好提神");
    }
}
