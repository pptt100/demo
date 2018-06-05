package com.jesse.decorate;

/**
 * Created by Jesse on 2018/5/30.
 */
public abstract class Decorate implements Drink {

    private Drink drink;

    public Decorate(Drink drink) {
        this.drink = drink;
    }

    @Override
    public void taste() {
        drink.taste();
    }
}
