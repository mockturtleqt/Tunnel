package com.epam.third.entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static com.epam.third.entity.ConstantHolder.MAX_INSTANCE_NUMBER;

public class Tunnel {
    private static Tunnel instance;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private Semaphore frontEntrance = new Semaphore(MAX_INSTANCE_NUMBER, true);
    private Semaphore backEntrance = new Semaphore(MAX_INSTANCE_NUMBER, true);

    private Tunnel() {}

    public static Tunnel getInstance() {
        if (instanceCounter.getAndIncrement() < 1) {
            instance = new Tunnel();
        }
        return instance;
    }

    public static AtomicInteger getInstanceCounter() {
        return instanceCounter;
    }

    public Semaphore getFrontEntrance() {
        return frontEntrance;
    }

    public Semaphore getBackEntrance() {
        return backEntrance;
    }

    @Override
    public String toString() {
        return "Tunnel{} " ;
    }
}
