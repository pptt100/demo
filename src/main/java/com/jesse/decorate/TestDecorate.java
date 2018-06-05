package com.jesse.decorate;

/**
 * Created by Jesse on 2018/5/30.
 */
public class TestDecorate {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        Ice ice = new Ice(coffee);
        ice.taste();
    }
}
