package com.epam.third.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.epam.third.entity.ConstantHolder.TRAIL_COUNT;

public class TunnelManager {
    private static Logger logger = Logger.getLogger(TunnelManager.class);
    private static Semaphore semaphore = new Semaphore(TRAIL_COUNT, true);
    private static AtomicInteger counter = new AtomicInteger(0);
    private static int lastTrainDirection;

    public static void getResource() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            logger.error(e, e);
        }
    }

    public static void returnResource() {
        semaphore.release();
    }

    public static AtomicInteger getCounter() {
        return counter;
    }

    public static void setCounter(AtomicInteger cnt) {
        counter = cnt;
    }

    public static int getLastTrainDirection() {
        return lastTrainDirection;
    }

    public static void setLastTrainDirection(int lastTrainDirection) {
        TunnelManager.lastTrainDirection = lastTrainDirection;
    }

    public static Semaphore getSemaphore() {
        return semaphore;
    }

    public static void setSemaphore(Semaphore semaphore) {
        TunnelManager.semaphore = semaphore;
    }

    public static void incrementCounter() {
        counter.getAndIncrement();
    }

    public static void setCounterToZero() {
        counter.set(0);
    }
}
