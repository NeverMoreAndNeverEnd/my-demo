package com.soul.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private Integer id;
    private String userName;
    private int age;

}

/**
 * 题目:请按照给出数据,找出同时满足以下条件的用户,也即以下条件全部满足
 * 偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 * 只输出一个用户名字
 */
public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        Function<String, Integer> function = s -> {
            return s.length();
        };
        System.out.println(function.apply("abc"));

        Predicate<String> predicate = s -> {
            return s.isEmpty();
        };
        System.out.println(predicate.test("xiass"));

        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("sss00");

        Supplier<String> supplier = ()->{
            return "123";
        };
        System.out.println(supplier.get());


    }
}
