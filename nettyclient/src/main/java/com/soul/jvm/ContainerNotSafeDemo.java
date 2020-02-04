package com.soul.jvm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 集合类不安全的问题
 * ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);
        Collections.synchronizedList(list);
        HashSet<Object> objects = new HashSet<>();

    }


}
