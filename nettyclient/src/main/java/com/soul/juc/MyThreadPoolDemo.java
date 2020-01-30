package com.soul.juc;

import java.util.concurrent.*;

public class MyThreadPoolDemo {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());
        //cpu密集型,maxPoolSize = Runtime.getRuntime().availableProcessors()+1
        //io密集型
        //initPool();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    private static void initPool() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一个池有5个受理线程,类似一个银行有5个受理窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一个池有1个受理线程,类似一个银行有1个受理窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();//一个池有n个受理线程,类似一个银行有n个受理窗口

        try {
            //模拟10个顾客过来银行办理业务,目前池子里有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                TimeUnit.MILLISECONDS.sleep(400);
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
