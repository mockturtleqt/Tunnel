package com.epam.third.entity;

import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.third.entity.TrainDirection.BACK;
import static com.epam.third.entity.TrainDirection.FRONT;

public class Tunnel {
    private static final Logger logger = Logger.getLogger(Tunnel.class);
    private final int MAX_TRAINS_IN_A_ROW = 3;
    private final Lock priorityLock = new ReentrantLock();
    private final Semaphore frontSemaphore = new Semaphore(MAX_TRAINS_IN_A_ROW, true);
    private final Semaphore backSemaphore = new Semaphore(MAX_TRAINS_IN_A_ROW, true);

    private TrainDirection priorityDirection = BACK;
    private AtomicInteger trainCounter = new AtomicInteger(0);
    private int tunnelId;

    public Tunnel(int id) {
        this.tunnelId = id;
    }

    public void occupyTunnel(Train train) {
        try {
            acquire(train.getDirection());

            while (!canEnterTunnel(train)) {
                TimeUnit.MICROSECONDS.sleep(1);
            }

            System.out.println("Train " + train.getTrainId() + " got tunnel " + this.getTunnelId() +
                    " dir " + train.getDirection() + " " + LocalTime.now());
            TimeUnit.SECONDS.sleep(5);

            if (trainCounter.get() > MAX_TRAINS_IN_A_ROW - 1) {
                trainCounter.set(0);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    public void releaseTunnel(Train train) {
        System.out.println("Train " + train.getTrainId() + " released tunnel " + this.getTunnelId() +
                " dir " + train.getDirection() + " " + LocalTime.now());
        release(train.getDirection());
    }

    private void acquire(TrainDirection direction) throws InterruptedException {
        if (direction == FRONT) {
            frontSemaphore.acquire();
        } else {
            backSemaphore.acquire();
        }
    }

    private void release(TrainDirection direction) {
        if (direction == FRONT) {
            frontSemaphore.release();
        } else {
            backSemaphore.release();
        }
    }

    private boolean isTunnelEmpty(TrainDirection direction) {
        return ((direction == FRONT ? backSemaphore : frontSemaphore).availablePermits() == MAX_TRAINS_IN_A_ROW);
    }

    private boolean isDirectionSame(Train train) {
        return (train.getDirection().equals(getPriorityDirection()));
    }

    private boolean canEnterTunnel(Train train) throws InterruptedException {
        boolean canEnter;
        try {
            priorityLock.lock();
            canEnter = (isTunnelEmpty(train.getDirection()) ||
                    (isDirectionSame(train) && (getTrainCounter().get() < MAX_TRAINS_IN_A_ROW)));
            if (canEnter) {
                trainCounter.getAndIncrement();
                if (trainCounter.get() == MAX_TRAINS_IN_A_ROW) {
                    priorityDirection = (priorityDirection.equals(FRONT)) ? BACK : FRONT;
                } else {
                    priorityDirection = train.getDirection();
                }
            }
        } finally {
            priorityLock.unlock();
        }
        return canEnter;
    }

    public TrainDirection getPriorityDirection() {
        return priorityDirection;
    }

    public AtomicInteger getTrainCounter() {
        return trainCounter;
    }

    public int getTunnelId() {
        return tunnelId;
    }
}
