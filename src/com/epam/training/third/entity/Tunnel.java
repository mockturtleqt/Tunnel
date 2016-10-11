package com.epam.training.third.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Tunnel {
    private static final int MAX_INSTANCE_NUMBER = 2;
    private static Tunnel instance = null;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);

    private Tunnel() {}

    public static Tunnel getInstance() {
        if (instanceCounter.getAndIncrement() < MAX_INSTANCE_NUMBER) {
            instance = new Tunnel();
        }
        return instance;
    }

    public static AtomicInteger getInstanceCounter() {
        return instanceCounter;
    }

    @Override
    public String toString() {
        return "Tunnel{} " ;
    }
}
