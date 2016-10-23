package com.epam.third.entity;

import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.epam.third.entity.TrainDirection.BACK;
import static com.epam.third.entity.TrainDirection.FRONT;

public class Tunnel {
    private final int MAX_TRAINS_IN_A_ROW = 3;
    private static Logger logger = Logger.getLogger(Tunnel.class);
    private final Semaphore semaphore = new Semaphore(MAX_TRAINS_IN_A_ROW, true);
    private TrainDirection priorityDirection = BACK;
    private AtomicInteger trainCounter = new AtomicInteger(0);
    private int tunnelId;

    public Tunnel(int id) {
        this.tunnelId = id;
    }

    public void occupyTunnel(Train train) {
        try {
            semaphore.acquire();
            System.out.println("Train " + train.getTrainId() + " got tunnel " + this.getTunnelId() + " " + LocalTime.now());
            TimeUnit.SECONDS.sleep(5);

            trainCounter.getAndIncrement();
            priorityDirection = train.getDirection();

            if (trainCounter.get() > MAX_TRAINS_IN_A_ROW - 1) {
                priorityDirection = (priorityDirection.equals(FRONT)) ? BACK : FRONT;
                trainCounter.set(0);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    public void releaseTunnel(Train train) {
        semaphore.release();
        System.out.println("Train " + train.getTrainId() + " releases tunnel " + this.getTunnelId() + " " + LocalTime.now());
    }

    public Semaphore getSemaphore() {
        return semaphore;
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
