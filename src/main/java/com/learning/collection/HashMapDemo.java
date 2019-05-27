package com.learning.collection;

import org.junit.Test;

public class HashMapDemo {
    
    static final int MAXIMUM_CAPACITY = 1 << 30;
    
    @Test
    public void test() {
        System.out.println(tableSizeFor(333));
//        System.out.println(tableSizeFor(13));
//        System.out.println(tableSizeFor(23));
//        System.out.println(tableSizeFor(333));
//        System.out.println(tableSizeFor(4443));
//        System.out.println(tableSizeFor(444443));
    }
    
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
