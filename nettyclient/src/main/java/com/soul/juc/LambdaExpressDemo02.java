package com.soul.juc;

@FunctionalInterface
interface Foo {
    //public void sayHello();
    public int add(int x, int y);

    public default int mul(int x, int y) {
        return x * y;
    }

    public static int xx(int x, int y) {
        return x / y;
    }
}

/**
 * lambda express 解决了匿名内部类代码冗余的现象
 * 1.拷贝中括号,写死右箭头,落地大括号
 * 2.@FunctionalInterface  函数式接口
 * 3.default
 * 4.static
 */
public class LambdaExpressDemo02 {

    public static void main(String[] args) {

       /* Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("***hello 1205");
            }

            @Override
            public int add(int x, int y) {
                return 0;
            }
        };
        foo.sayHello();*/

        Foo foo = (int x, int y) -> {
            System.out.println("come in add method ");
            return x + y;
        };
        System.out.println(foo.add(1, 2));
        System.out.println(foo.mul(3, 5));
        System.out.println(Foo.xx(10, 2));
    }
}
