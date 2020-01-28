package com.soul.juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("********sendEmail");
    }

    public  synchronized void sendSMS() throws Exception {
        System.out.println("********sendSMS");
    }

    public void sayHello() throws Exception {
        System.out.println("********sayHello");
    }
}

/**
 * 1.标准访问,请问先打印邮件还是短信     邮件
 * 2.暂停4s在邮件方法,请问是先打印邮件还是短信   邮件
 * 3.新增普通sayHello方法,请问先打印邮件还是hello  hello
 * 4.两部手机,先打印邮件还是短信  短信
 * 5.两个静态同步方法,同一部手机,打印手机还是短信  邮件
 * 6.两个静态同步方法,两部手机,打印手机还是短信   邮件
 * 7.一个静态同步方法,一个普通同步方法,一部手机,打印手机还是短信   短信
 * 8.一个静态同步方法,一个普通同步方法,两部手机,打印手机还是短信   短信
 */
public class Lock8Demo05 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                //phone.sendSMS();
                //phone.sayHello();
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();

    }

}
