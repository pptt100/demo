package com.jesse.decorate;

/**
 * Created by Jesse on 2018/5/30.
 */
public class Sugar extends Decorate {
    public Sugar(Drink drink) {
        super(drink);
    }

    @Override
    public void taste() {
        System.out.println("加糖了，味道甜多了！");
    }
}
