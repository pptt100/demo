package com.jesse.decorate;

/**
 * Created by Jesse on 2018/5/30.
 */
public class Milk extends Decorate {
    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public void taste() {
        System.out.println("加奶了，味道绵绸多了！");
    }
}
