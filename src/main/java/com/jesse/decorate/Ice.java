package com.jesse.decorate;

/**
 * Created by Jesse on 2018/5/30.
 */
public class Ice extends Decorate {
    public Ice(Drink drink) {
        super(drink);
    }

    @Override
    public void taste() {
        System.out.println("加冰了，好凉爽");
    }
}
