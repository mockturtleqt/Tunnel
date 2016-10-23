package com.epam.third.service;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;
import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TunnelManager {
    private final int MAX_TRAINS_IN_A_ROW = 3;
    private static Logger logger = Logger.getLogger(TunnelManager.class);
    private static TunnelManager instance;
    private static AtomicInteger instanceCounter = new AtomicInteger(0);
    private Tunnel tunnel1 = new Tunnel(1);
    private Tunnel tunnel2 = new Tunnel(2);
    private Queue<Train> trains = new ArrayDeque<>();

    private TunnelManager(Queue<Train> trains) {
        this.trains = trains;
    }

    public static TunnelManager getInstance(Queue<Train> trains) {
        if (instanceCounter.getAndIncrement() < 1) {
            instance = new TunnelManager(trains);
        }
        return instance;
    }

    public void processTrains() {
        for (Train train : trains) {
            while (!canRun(train)) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
            train.setTunnel(getAvailableTunnel(train));
            train.start();
        }
    }

    private boolean canRun(Train train) {
        return (canEnterTunnel(train, tunnel1) || canEnterTunnel(train, tunnel2));
    }

    private Tunnel getAvailableTunnel(Train train) {
        return (canEnterTunnel(train, tunnel1)) ? tunnel1 : tunnel2;
    }

    private boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == MAX_TRAINS_IN_A_ROW);
    }

    private boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection().equals(tunnel.getPriorityDirection()));
    }

    private boolean canEnterTunnel(Train train, Tunnel tunnel) {
        return (isTunnelEmpty(tunnel) ||
                (isDirectionSame(train, tunnel) && (tunnel.getTrainCounter().get() < MAX_TRAINS_IN_A_ROW)));
    }
}
