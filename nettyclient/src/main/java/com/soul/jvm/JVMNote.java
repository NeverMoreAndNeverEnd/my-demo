package com.soul.jvm;

class MyNumber{
    volatile int number = 10;

    public void addTo1205(){
        this.number = 1205;
    }
}

public class JVMNote {

    public static void main(String[] args) {

        MyNumber myNumber = new MyNumber();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"********come in");
                Thread.sleep(3000);
                myNumber.addTo1205();//将10修改为1205
                System.out.println(Thread.currentThread().getName()+"\t A update number,number value :"+myNumber.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        while (myNumber.number == 10){
            //需要有一种通知机制告诉main线程,number已经修改为1205,跳出while

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");


    }

    private static void jvm() {
        System.out.println(Runtime.getRuntime().availableProcessors());

        long maxMemory = Runtime.getRuntime().maxMemory();//返回java虚拟机试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中的内存总量
        System.out.println("-Xmx:max_memory =" + maxMemory + "字节," + (maxMemory / (double) 1024 / 1024) + "mb");
        System.out.println("-Xms:total_memory =" + totalMemory + "字节," + (totalMemory / (double) 1024 / 1024) + "mb");
    }


}
