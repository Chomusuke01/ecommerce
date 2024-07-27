package com.ob.ecommerce.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {

    private static final AtomicInteger counter = new AtomicInteger();
    public static int generateID(){
        return counter.incrementAndGet();
    }
}
