package com.soul.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket { //资源类=实例变量+实例方法
    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "\t 还剩下:" + number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}


public class SaleTicketDemo01 {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();*/

        new Thread(() ->{ for (int i = 1; i <=40 ; i++) ticket.sale(); },"A").start();
        new Thread(() ->{ for (int i = 1; i <=40 ; i++) ticket.sale(); },"B").start();
        new Thread(() ->{ for (int i = 1; i <=40 ; i++) ticket.sale(); },"C").start();

    }

}
