package com.soul.interview;

import java.util.concurrent.atomic.AtomicReference;

public class SingletonAR {

    private static final AtomicReference<SingletonAR> instance = new AtomicReference<>();

    private SingletonAR() {

    }

    public static final SingletonAR getInstance() {
        while (true) {
            SingletonAR current = instance.get();
            if (current != null) {
                return current;
            }
            current = new SingletonAR();
            if (instance.compareAndSet(null, current)) {
                return current;
            }
        }
    }


}
