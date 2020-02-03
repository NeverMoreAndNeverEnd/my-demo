package com.soul.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS是什么? ===>compareAndSet
 * 比较并交换
 * 如果线程的期望值和物理内存的真实值一样,就修改更新值,本次操作true
 * 如果期望值和真实值不一样,false,本次操作失败,需要重新获得主物理内存的真实值
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data:"+atomicInteger.get());
        atomicInteger.getAndIncrement();
    }

}
