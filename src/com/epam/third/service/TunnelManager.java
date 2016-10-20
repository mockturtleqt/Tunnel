package com.epam.third.service;

import com.epam.third.entity.Train;
import com.epam.third.entity.Tunnel;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class TunnelManager {
    private static Logger logger = Logger.getLogger(TunnelManager.class);
    private static final int TRAIL_COUNT = 2;
    private static final int MAX_TRAINS_IN_A_ROW = 3;

    private static Tunnel tunnel = Tunnel.getInstance(1);

    private static boolean isTunnelEmpty(Tunnel tunnel) {
        return (tunnel.getSemaphore().availablePermits() == TRAIL_COUNT);
    }

    private static boolean isDirectionSame(Train train, Tunnel tunnel) {
        return (train.getDirection().equals(tunnel.getLastTrainDirection()));
    }

    private static boolean canEnterTunnel(Train train, Tunnel tunnel) {
        return (isTunnelEmpty(tunnel) ||
                (!isTunnelEmpty(tunnel) && isDirectionSame(train, tunnel) && (tunnel.getTrainCounter().get() < MAX_TRAINS_IN_A_ROW)));
    }

    public static void processTrain(Train train) {
        while (!canEnterTunnel(train, tunnel)) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        try {
            tunnel.occupyTrail();

            if (tunnel.getLastTrainDirection().equals(train.getDirection())) {
                tunnel.incrementCounter();
            } else {
                tunnel.setCounterToZero();
            }
            tunnel.setLastTrainDirection(train.getDirection());
            System.out.println("Train num " + train.getName() +
                    " dir " + train.getDirection() +
                    " tunnel dir " + tunnel.getLastTrainDirection() +
                    " count = " + tunnel.getTrainCounter() +
                    " " + tunnel + " " + LocalTime.now());

        } finally {
            tunnel.releaseTrail();
        }
    }

}
