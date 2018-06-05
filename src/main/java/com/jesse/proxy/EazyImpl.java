package com.jesse.proxy;

/**
 * Created by Jesse on 2018/5/30.
 */
public class EazyImpl implements MathCalculator {
    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i/j;
    }
}
