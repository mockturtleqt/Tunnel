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

    public boolean occupyTunnel(Train train) {
        try {
            semaphore.acquire();
            if (!canEnterTunnel(train, this)) {
                return false;
            }

            trainCounter.getAndIncrement();
            priorityDirection = train.getDirection();

            System.out.println("Train " + train.getTrainId() + " got tunnel " + this.getTunnelId() + " " + train.getDirection() + " " + LocalTime.now());
            TimeUnit.SECONDS.sleep(5);

            if (trainCounter.get() > MAX_TRAINS_IN_A_ROW - 1) {
                priorityDirection = (priorityDirection.equals(FRONT)) ? BACK : FRONT;
                trainCounter.set(0);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return true;
    }

    public void releaseTunnel(Train train) {
        System.out.println("Train " + train.getTrainId() + " releases tunnel " + this.getTunnelId() + " " + train.getDirection() + " " + LocalTime.now());
        semaphore.release();
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

    private boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == MAX_TRAINS_IN_A_ROW - 1);
    }

    private boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection().equals(tunnel.getPriorityDirection()));
    }

    private boolean canEnterTunnel(Train train, Tunnel tunnel) {
        return (isTunnelEmpty(tunnel) ||
                (isDirectionSame(train, tunnel) && (tunnel.getTrainCounter().get() < MAX_TRAINS_IN_A_ROW)));
    }
}
