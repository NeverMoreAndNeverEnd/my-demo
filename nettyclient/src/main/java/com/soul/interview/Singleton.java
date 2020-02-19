package com.soul.interview;

public enum Singleton {
    INSTANCE {
        @Override
        protected void doSomething() {
            System.out.println("doSomething");
        }
    };

    protected abstract void doSomething();


}

class Test {
    public static void main(String[] args) {
        Singleton s1 = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;
        System.out.println(s1 == s2);
    }

}
