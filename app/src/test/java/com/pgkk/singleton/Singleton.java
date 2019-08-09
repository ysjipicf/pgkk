package com.pgkk.singleton;

/**
 * Created by tanxueze on 2019/2/20.
 */

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public Object readResolve() {
        return instance;
    }
}
