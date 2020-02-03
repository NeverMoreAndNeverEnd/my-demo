package com.soul.jvm;

import java.util.concurrent.atomic.AtomicInteger;

class MyNumber {
    volatile int number = 10;

    public void addTo1205() {
        this.number = 1205;
    }

    public void addPlus() {
        number++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.加sync,不过太重
 * 2.atomicInteger
 *
 */
public class JVMNote {

    public static void main(String[] args) {

        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlus();
                    myNumber.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上面20个线程全部计算完成后,再用main线程取得最终的结果值
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +"\t finally number value :"+myNumber.number);
        System.out.println(Thread.currentThread().getName() +"\t finally number value :"+myNumber.atomicInteger);


    }

    private static void seeOkByVolatile() {
        MyNumber myNumber = new MyNumber();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "********come in");
                Thread.sleep(3000);
                myNumber.addTo1205();//将10修改为1205
                System.out.println(Thread.currentThread().getName() + "\t A update number,number value :" + myNumber.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        while (myNumber.number == 10) {
            //需要有一种通知机制告诉main线程,number已经修改为1205,跳出while

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

    private static void jvm() {
        System.out.println(Runtime.getRuntime().availableProcessors());

        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中的内存总量
        System.out.println("-Xmx:max_memory =" + maxMemory + "字节," + (maxMemory / (double) 1024 / 1024) + "mb");
        System.out.println("-Xms:total_memory =" + totalMemory + "字节," + (totalMemory / (double) 1024 / 1024) + "mb");
    }


}
