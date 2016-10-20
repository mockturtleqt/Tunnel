package com.epam.third.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Tunnel {
    private static final int TRAIL_COUNT = 2;
    private static final int MAX_INSTANCE_NUMBER = 1;
    private static Logger logger = Logger.getLogger(Tunnel.class);
    private static Tunnel instance;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private Semaphore semaphore = new Semaphore(TRAIL_COUNT, true);
    private AtomicInteger trainCounter = new AtomicInteger(0);
    private TrainDirection lastTrainDirection = TrainDirection.BACK;
    private int id;

    private Tunnel(int id) {
        this.id = id;
    }

    public static Tunnel getInstance(int id) {
        if (instanceCounter.getAndIncrement() < MAX_INSTANCE_NUMBER) {
            instance = new Tunnel(id);
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

    public TrainDirection getLastTrainDirection() {
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

    public void setLastTrainDirection(TrainDirection lastTrainDirection) {
        this.lastTrainDirection = lastTrainDirection;
    }

    @Override
    public String toString() {
        return "Tunnel{" + id + "} ";
    }
}
