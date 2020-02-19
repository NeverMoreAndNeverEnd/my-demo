package com.soul.interview;

public class SingletonTL {

    private static SingletonTL instance = null;

    private SingletonTL() {

    }

    private static final ThreadLocal<SingletonTL> threadLocalSingleton = new ThreadLocal<SingletonTL>() {
        @Override
        protected SingletonTL initialValue() {
            return new SingletonTL();
        }
    };

    public static SingletonTL getInstance() {
        return threadLocalSingleton.get();
    }

}
