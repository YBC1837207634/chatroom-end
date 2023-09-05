package com.example.common;

public class BaseContent {
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void set(Integer id) {
        threadLocal.set(id);
    }
    public static Integer get() {
        return threadLocal.get();
    }

}
