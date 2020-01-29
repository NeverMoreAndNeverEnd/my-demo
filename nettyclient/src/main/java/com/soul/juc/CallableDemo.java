package com.soul.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("************come in call");
        return 1024;
    }
}

/**
 * java多线程,第三种获得多线程的方式
 *
 * 1.get方法一般请放在最后一行(会阻塞)
 * 多个线程调用同一个futureTask,只会计算(调用)一次
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "A").start();
        Integer result = futureTask.get();
        System.out.println(result);

    }
}
