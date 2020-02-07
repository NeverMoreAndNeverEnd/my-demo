package com.soul.oom;

import java.util.Random;

public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        String str = "atguigu";
        while (true){
            str += str+new Random().nextInt(1111111)+new Random().nextInt(22222222);
            str.intern();
            //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        }
    }
}
