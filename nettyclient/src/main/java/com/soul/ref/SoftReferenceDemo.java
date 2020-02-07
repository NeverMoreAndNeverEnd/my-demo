package com.soul.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void soft_memory_enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());


    }

    /**
     * jvm 配置,故意产生大对象配置小内存,让内存不够用产生oom,看软引用回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void soft_memory_notEnough() {

        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }


    public static void main(String[] args) {

        soft_memory_enough();

        //soft_memory_notEnough();

    }

}
