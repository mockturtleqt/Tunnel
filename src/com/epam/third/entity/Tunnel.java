package com.epam.third.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.epam.third.entity.ConstantHolder.TRAIL_COUNT;

public class Tunnel {
    private static Logger logger = Logger.getLogger(Tunnel.class);
    private static Tunnel instance;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private Semaphore semaphore = new Semaphore(TRAIL_COUNT, true);
    private AtomicInteger trainCounter = new AtomicInteger(0);
    private int lastTrainDirection;

    private Tunnel() {
    }

    public static Tunnel getInstance() {
        if (instanceCounter.getAndIncrement() < 1) {
            instance = new Tunnel();
        }
        return instance;
    }

    public void occupyTrail() {
        try {
            semaphore.acquire();
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.error(e, e);
        }
    }

    public void releaseTrail() {
        semaphore.release();
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public int getLastTrainDirection() {
        return lastTrainDirection;
    }

    public AtomicInteger getTrainCounter() {
        return trainCounter;
    }

    public void incrementCounter() {
        trainCounter.getAndIncrement();
    }

    public void setCounterToZero() {
        trainCounter.set(0);
    }

    public void setLastTrainDirection(int lastTrainDirection) {
        this.lastTrainDirection = lastTrainDirection;
    }

    @Override
    public String toString() {
        return "Tunnel{} ";
    }
}
